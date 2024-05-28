package lv.venta.service;

import lv.venta.model.Address;

public interface ICustomerService {

	public abstract void insertNewCustomerAsPerson(String name, String surname, String personCode, String phoneNo) throws Exception;

	public abstract void insertNewCustomerAsCompany(String phoneNo, String title, String companyRegNo) throws Exception;
	
	public abstract void addAddressToCustomerByCustomerId(int customerId, Address address) throws Exception;
	
}
