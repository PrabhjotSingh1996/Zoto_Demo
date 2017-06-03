package zoto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zoto.demo.common.Request;
import zoto.demo.common.Response;
import zoto.demo.service.RechargeService;

@RestController
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;
	
	@RequestMapping(value="/recharge/{accessToken}/", method = RequestMethod.PUT)
	public Response recharge(@PathVariable(value="accessToken") String accessToken,@RequestBody Request request){
		return rechargeService.recharge(accessToken, request);
	}
	
	@RequestMapping(value="/history/{accessToken}/", method = RequestMethod.GET)
	public Response getHistory(@PathVariable(value="accessToken") String accessToken){
		return rechargeService.getHistory(accessToken);
	}
	
}
