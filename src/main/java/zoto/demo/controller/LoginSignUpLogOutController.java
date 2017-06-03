package zoto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zoto.demo.common.Request;
import zoto.demo.common.Response;
import zoto.demo.service.LoginSignUpLogOutService;

@RestController
public class LoginSignUpLogOutController {
	
	@Autowired
	private LoginSignUpLogOutService loginSignUpLogOutService;
	
	@RequestMapping(value= "/login/{msisdn}/{pin}/" ,method = RequestMethod.GET)
	public Response login(@PathVariable(value = "msisdn") Long msisdn,@PathVariable(value = "pin") String pin){
		return loginSignUpLogOutService.login(msisdn,pin);
	}

	@RequestMapping(value= "/signup/" ,method = RequestMethod.POST)
	public Response login(@RequestBody Request request){
		return loginSignUpLogOutService.signUp(request);
	}
	
	@RequestMapping(value= "/logout/{accessToken}/" ,method = RequestMethod.PUT)
	public Response login(@PathVariable(value = "accessToken") String accessToken){
		return loginSignUpLogOutService.logOut(accessToken);
	}
}
