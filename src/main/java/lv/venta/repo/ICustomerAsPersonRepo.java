package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Integer>{

	boolean existsByPersonCode(String personCode);

	boolean existsByCustomerId(int id);

	boolean existsByCustomerCode(String customerCode);

	boolean existsByCustomerId(String customerId);

	CustomerAsPerson findByCustomerId(int customerId);

}
