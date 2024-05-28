package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Integer>{

	boolean existsByCustomerId(int id);

	boolean existsByCustomerCode(String customerCode);

}
