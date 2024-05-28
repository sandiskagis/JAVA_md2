package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.AbstractCustomer;

public interface IAbstractCustomerRepo extends CrudRepository<AbstractCustomer, Integer>{
	AbstractCustomer findByCustomerCode(String customerCode);

	boolean existsByCustomerId(int id);

	boolean existsByCustomerCode(String customerCode);

}
