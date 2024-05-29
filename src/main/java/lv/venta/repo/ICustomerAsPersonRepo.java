package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Integer>{

	boolean existsByPersonPersonCode(String personCode);

	boolean existsByCustomerId(int id);

	boolean existsByCustomerCode(String customerCode);

	CustomerAsPerson findByCustomerId(int customerId);
	
	CustomerAsPerson findByCustomerCode(String customerCode);

}
