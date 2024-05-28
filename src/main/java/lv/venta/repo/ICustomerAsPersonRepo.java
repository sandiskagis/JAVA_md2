package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Integer>{

	boolean existsByCustomerId(int id);

	boolean existsByCustomerCode(String customerCode);

}
