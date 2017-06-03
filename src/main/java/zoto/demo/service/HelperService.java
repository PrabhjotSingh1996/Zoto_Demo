package zoto.demo.service;

import zoto.demo.common.Response;

public interface HelperService {

	public void setError(Response response,String message);
	
	public boolean verifyAccess(String accessToken);
	
}
