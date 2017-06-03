package zoto.demo.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zoto.demo.common.Response;
import zoto.demo.service.GeneralService;
import zoto.demo.service.HelperService;

@Service
public class GeneralServiceImpl implements GeneralService {
	
	@Autowired
	private HelperService helperService;

	@Override
	public Response home(String accessToken){
		Response response = new Response();
		if(!helperService.verifyAccess(accessToken)) helperService.setError(response, "Session Expired. Please Login again");
		else{
			response.setEndPoints(new HashMap<>());
			response.getEndPoints().put("Recharge", "http://localhost:8080/recharge/"+accessToken+"/");
			response.getEndPoints().put("Transaction History", "http://localhost:8080/history/"+accessToken+"/");
			response.getEndPoints().put("LogOut", "http://localhost:8080/logout/"+accessToken+"/");
		}
		return response;
	}
	
}
