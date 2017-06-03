package zoto.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zoto.demo.model.TransactionHistory;

public interface TransactionHistoryDao extends CrudRepository<TransactionHistory,Long>{
	
	public List<TransactionHistory> findByPayer(Long payer);

}
