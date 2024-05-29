package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Person;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerAsPersonRepo customerAsPersonRepo;
	
	@Autowired
	ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	
	@Override
	public void insertNewCustomerAsPerson(String name, String surname, String personCode, String phoneNo) throws Exception {
		if(customerAsPersonRepo.existsByPersonPersonCode(personCode))
			throw new Exception("This CustomerAsPerson is already registered!");
		
		//CustomerAsPerson newCustomer = new CustomerAsPerson(name, surname, personCode, null, phoneNo);
		//customerAsPersonRepo.save(newCustomer);
		
		Person newPerson = new Person(name, surname, personCode);
		CustomerAsPerson newCustomer = new CustomerAsPerson(newPerson, null, phoneNo);
		customerAsPersonRepo.save(newCustomer);
		
		//todo no sakuma jasaglaba persona personRepo, tad so pasu personu pielipina customerAsPerson un saglaba customerAsPersonRepo
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
