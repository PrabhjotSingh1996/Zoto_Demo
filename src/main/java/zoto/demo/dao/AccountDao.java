package zoto.demo.dao;

import org.springframework.data.repository.CrudRepository;

import zoto.demo.model.Account;

public interface AccountDao extends CrudRepository<Account,String>{
	
}
