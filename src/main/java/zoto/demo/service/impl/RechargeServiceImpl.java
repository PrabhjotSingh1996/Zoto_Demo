package zoto.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zoto.demo.common.Request;
import zoto.demo.common.Response;
import zoto.demo.dao.AccountDao;
import zoto.demo.dao.OperatorDao;
import zoto.demo.dao.TransactionHistoryDao;
import zoto.demo.dao.UserDetailsDao;
import zoto.demo.model.Account;
import zoto.demo.model.Operator;
import zoto.demo.model.TransactionHistory;
import zoto.demo.model.UserDetails;
import zoto.demo.service.HelperService;
import zoto.demo.service.RechargeService;

@Service
public class RechargeServiceImpl implements RechargeService {
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired 
	private OperatorDao operatorDao;
	
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;
	
	@Override
	public Response recharge(String accessToken,Request request){
		Response response = new Response();
		if(!helperService.verifyAccess(accessToken)){
			helperService.setError(response, "Invalid access.Please Login again");
		}
		else if(null==request.getRecharge() || null==request.getRecharge().getMsisdn() || null==request.getRecharge().getPassword()){
			helperService.setError(response, "Incomplete Information");
		}
		else{
			UserDetails userDetails = userDetailsDao.findByAccessToken(accessToken);
			Account account = accountDao.findOne(userDetails.getAccountNumber());
			Operator operator = operatorDao.findOne(request.getRecharge().getMsisdn());
			if(null == account) helperService.setError(response, "No account found");
			else if(null == operator) helperService.setError(response, "Invalid MSISDN");
			else if(!account.getPassword().equalsIgnoreCase(request.getRecharge().getPassword())) helperService.setError(response, "Incorrect Account password");
			else if(account.getBalance() < request.getRecharge().getAmount()) helperService.setError(response, "Insufficent Balance");
			else{
				accountDao.delete(account);
				account.setBalance(account.getBalance()-request.getRecharge().getAmount());
				accountDao.save(account);
				operatorDao.delete(operator);
				operator.setBalance(operator.getBalance()+request.getRecharge().getAmount());
				operatorDao.save(operator);
				transactionHistoryDao.save(new TransactionHistory(userDetails.getMsisdn(),request.getRecharge().getMsisdn(),
											request.getRecharge().getAmount(),Timestamp.valueOf(LocalDateTime.now())));
				response.setMessage("Recharge Successfull");
				response.setEndPoints(new HashMap<>());
				response.getEndPoints().put("Transaction History", "http://localhost:8080/history/"+accessToken+"/");
				response.getEndPoints().put("Home", "http://localhost:8080/home/"+accessToken+"/");
			}
		}
		return response;
	}

	@Override
	public Response getHistory(String accessToken) {
		Response response = new Response();
		if(!helperService.verifyAccess(accessToken)) helperService.setError(response, "Invalid Access please Login Again");
		else{
			UserDetails userDetails = userDetailsDao.findByAccessToken(accessToken);
			response.setTransactionHistory(transactionHistoryDao.findByPayer(userDetails.getMsisdn()));
			response.setEndPoints(new HashMap<>());
			response.getEndPoints().put("Home", "http://localhost:8080/home/"+accessToken+"/");
		}
		return response;
	}

}
