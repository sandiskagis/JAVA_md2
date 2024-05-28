package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerAsPersonRepo customerAsPersonRepo;
	
	@Autowired
	ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	IAbstractCustomerRepo abstractCustomerRepo;
	
	
	@Override
	public void insertNewCustomerAsPerson(String name, String surname, String personCode, String phoneNo) throws Exception {
		if(customerAsPersonRepo.existsByPersonCode(personCode))
			throw new Exception("This CustomerAsPerson is already registered!");
		
		CustomerAsPerson newCustomer = new CustomerAsPerson(name, surname, personCode, null, phoneNo);
		customerAsPersonRepo.save(newCustomer);
	}
	
	@Override
	public void insertNewCustomerAsCompany(String phoneNo, String title, String companyRegNo) throws Exception {
		if(customerAsCompanyRepo.existsByCompanyRegNo(companyRegNo))
			throw new Exception("This CustomerAsCompany is already registered!");
		
		CustomerAsCompany newCustomer = new CustomerAsCompany(null, phoneNo, title, companyRegNo);
		customerAsCompanyRepo.save(newCustomer);
	}

	@Override
	public void addAddressToCustomerByCustomerId(int customerId, Address address) throws Exception {
	
		if(!customerAsPersonRepo.existsByCustomerId(customerId) && !customerAsCompanyRepo.existsByCustomerId(customerId))
			throw new Exception("No customer with such customer ID found!");
		
		if(customerAsPersonRepo.existsByCustomerId(customerId)) {
			CustomerAsPerson customer = customerAsPersonRepo.findByCustomerId(customerId);
			
			customer.setAddress(address);
		}
		
		
	}
	
}
