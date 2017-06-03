package zoto.demo.service;

import zoto.demo.common.Request;
import zoto.demo.common.Response;

public interface LoginSignUpLogOutService {

	public Response login(Long msisdn , String pin);
	
	public Response signUp(Request request);
	
	public Response logOut(String accessToken);
	
}
