package zoto.demo.dao;

import org.springframework.data.repository.CrudRepository;

import zoto.demo.model.UserDetails;

public interface UserDetailsDao extends CrudRepository<UserDetails, Long> {
	
	public UserDetails findByMsisdnAndPin(Long msisdn,String pin);
	
	public UserDetails findByAccessToken(String accessToken);

}
