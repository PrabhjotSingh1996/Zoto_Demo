package zoto.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zoto.demo.common.Error;
import zoto.demo.common.Response;
import zoto.demo.dao.UserDetailsDao;
import zoto.demo.service.HelperService;

@Service
public class HelperServiceImpl implements HelperService{
	
	@Autowired
	private UserDetailsDao userDetailsDao;

	@Override
	public void setError(Response response,String errorMessage){
		response.setError(new Error());
		response.getError().setErrorMessage(errorMessage);
	}

	@Override
	public boolean verifyAccess(String accessToken) {
		if(null != userDetailsDao.findByAccessToken(accessToken)) return true;
		return false;
	}
	
}
