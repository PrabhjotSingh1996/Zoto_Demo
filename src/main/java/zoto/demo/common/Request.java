package zoto.demo.common;

import zoto.demo.model.UserDetails;

public class Request {
	
	private UserDetails userDetails;
	private Recharge recharge;

	public Recharge getRecharge() {
		return recharge;
	}

	public void setRecharge(Recharge recharge) {
		this.recharge = recharge;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
