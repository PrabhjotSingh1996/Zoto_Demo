package zoto.demo.service;

import zoto.demo.common.Request;
import zoto.demo.common.Response;

public interface RechargeService {

	public Response recharge(String accessToken,Request request);
	
	public Response getHistory(String accessToken);
	
}
