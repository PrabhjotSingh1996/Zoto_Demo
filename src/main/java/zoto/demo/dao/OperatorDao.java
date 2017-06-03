package zoto.demo.dao;

import org.springframework.data.repository.CrudRepository;

import zoto.demo.model.Operator;

public interface OperatorDao extends CrudRepository<Operator,Long> {

}
