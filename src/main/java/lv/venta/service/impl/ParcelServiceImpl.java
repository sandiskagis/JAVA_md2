package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Parcel;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.service.IParcelService;

@Service
public class ParcelServiceImpl implements IParcelService{
	
	@Autowired
	IDriverRepo driverRepo;
	
	@Autowired
	IParcelRepo parcelRepo;
	
	@Autowired
	ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	ICustomerAsPersonRepo customerAsPersonRepo;

	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		if(id <= 0) throw new Exception("Id should be positive");
		
		if(!customerAsCompanyRepo.existsById(id) && !customerAsPersonRepo.existsById(id))
			throw new Exception("Customer with id (" + id + ") doesn't exist");
		
		ArrayList<Parcel> result = parcelRepo.findByCustomerId(id);
		
		if(result.isEmpty())
			throw new Exception("There is no linkage between this customer and parcels");		

		return result;
	}
	

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parcel insertNewParcelByCustomerCodeAndDriverId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeParcelDriverByParcelIdAndDriverId() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateIncomeOfParcelsByCustomerId() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
