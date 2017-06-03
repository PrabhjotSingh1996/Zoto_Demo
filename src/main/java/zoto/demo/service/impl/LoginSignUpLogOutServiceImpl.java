package zoto.demo.service.impl;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zoto.demo.common.Request;
import zoto.demo.common.Response;
import zoto.demo.dao.UserDetailsDao;
import zoto.demo.model.UserDetails;
import zoto.demo.service.HelperService;
import zoto.demo.service.LoginSignUpLogOutService;

@Service
public class LoginSignUpLogOutServiceImpl implements LoginSignUpLogOutService {
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private HelperService helperService;

	@Override
	public Response login(Long msisdn, String pin) {
		Response response = new Response();
		UserDetails userDetails = userDetailsDao.findByMsisdnAndPin(msisdn, pin);
		response.setEndPoints(new HashMap<>());
		if(userDetails != null){
			userDetails.setAccessToken(UUID.randomUUID().toString());
			userDetailsDao.delete(msisdn);
			userDetailsDao.save(userDetails);
			response.setAccessToken(userDetails.getAccessToken());
			response.setMessage("Welcome "+userDetails.getName());
			response.getEndPoints().put("Home", "http://localhost:8080/home/"+userDetails.getAccessToken()+"/");
		}
		else{
			helperService.setError(response, "MSISDN or PIN incorrect");
			response.getEndPoints().put("Signup", "http://localhost:8080/signup/");
		}
		return response;
	}
	
	@Override
	public Response signUp(Request request){
		Response response = new Response();
		UserDetails userDetails = request.getUserDetails();
		if( null== userDetails || null==userDetails.getAccountNumber() || null==userDetails.getMsisdn() ||
			null==userDetails.getName() || null==userDetails.getPin()){
			helperService.setError(response, "Incomplete information.All fields are Mandatory");
			return response;
		}
		else if(userDetailsDao.exists(userDetails.getMsisdn())){
			helperService.setError(response, "User already exists");
		}
		else{
			userDetails.setAccessToken(null);
			userDetailsDao.save(userDetails);
			response.setMessage("Signup Successfull");
			response.setEndPoints(new HashMap<>());
			response.getEndPoints().put("Login","http://localhost:8080/login/"+request.getUserDetails().getMsisdn()+"/"+request.getUserDetails().getPin()+"/");
		}
		return response;
	}

	@Override
	public Response logOut(String accessToken) {
		Response response = new Response();
		if(!helperService.verifyAccess(accessToken)) helperService.setError(response, "Session Expired");
		else{
			UserDetails userDetails = userDetailsDao.findByAccessToken(accessToken);
			userDetailsDao.delete(userDetails);
			userDetails.setAccessToken(null);
			userDetailsDao.save(userDetails);
			response.setMessage("Logged Out Successfully");
		}
		return response;
	}
	
	

}
