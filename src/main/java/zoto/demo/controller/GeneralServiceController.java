package zoto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zoto.demo.common.Response;
import zoto.demo.service.GeneralService;

@RestController
public class GeneralServiceController {

	@Autowired
	private GeneralService generalService;
	
	@RequestMapping(value="/home/{accessToken}/" , method= RequestMethod.GET)
	public Response home(@PathVariable(value="accessToken") String accessToken){
		return generalService.home(accessToken);
	}
	
}
