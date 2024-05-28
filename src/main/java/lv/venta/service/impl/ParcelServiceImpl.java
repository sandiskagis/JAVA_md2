package lv.venta.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.City;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.ParcelSize;
import lv.venta.repo.IAbstractCustomerRepo;
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
	
	@Autowired
	IAbstractCustomerRepo abstractCustomerRepo;

	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		if(id <= 0) throw new Exception("Id should be positive");
		
		if(!customerAsCompanyRepo.existsByCustomerId(id) && !customerAsPersonRepo.existsByCustomerId(id))
			throw new Exception("Customer with id (" + id + ") doesn't exist");
		
		ArrayList<Parcel> result = parcelRepo.findByCustomerId(id);
		
		if(result.isEmpty())
			throw new Exception("There is no linkage between this customer and parcels");		

		return result;
	}
	

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		if(id <= 0) throw new Exception("Id should be positive");
		
		if(!driverRepo.existsById(id))
			throw new Exception("Driver with id (" + id + ") doesn't exist");
		
		ArrayList<Parcel> result = parcelRepo.findByDriverId(id);
		
		if(result.isEmpty())
			throw new Exception("There is no linkage between this driver and parcels");		

		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception {
		if(price < 0 || price > 10000) throw new Exception("The limit of price is wrong");
		
		ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		if(city == null) throw new Exception("Wrong city input param");
		
		ArrayList<Parcel> result = parcelRepo.findByCity(city);
		return result;
	}

	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customerCode, int driverId, boolean isFragile, LocalDateTime orderCreated, LocalDateTime plannedDelivery, float price, ParcelSize size, Driver driver) throws Exception {
		if(driverId <= 0) throw new Exception("Id should be positive");
		
		if(customerCode == null) throw new Exception("Problems with customerCode input parameter");
		
		if(!customerAsCompanyRepo.existsByCustomerCode(customerCode) || !customerAsPersonRepo.existsByCustomerCode(customerCode))
			throw new Exception("Customer with customerCode (" + customerCode + ") doesn't exist");
		
		if(driverRepo.existsById(driverId))
			driver = driverRepo.findByPersonId(driverId);
			//throw new Exception("Driver with id (" + driverId + ") doesn't exist");
		
		if(abstractCustomerRepo.existsByCustomerCode(customerCode)) {
			AbstractCustomer tempSt = abstractCustomerRepo.findByCustomerCode(customerCode);
			tempSt.addNewParcel(new Parcel(isFragile, size, driver, plannedDelivery));
			
			abstractCustomerRepo.save(tempSt);
		}
		
	}

	@Override
	public void changeParcelDriverByParcelIdAndDriverId(int parcelId, int driverId) throws Exception {
		if(parcelId <= 0) throw new Exception("Parcel ID should be positive");
		if(driverId <= 0) throw new Exception("Driver ID should be positive");
		
		Parcel parcelToChange = parcelRepo.findByParcelId(parcelId);
		if (parcelToChange == null) {
	        throw new Exception("Parcel not found for ID: " + parcelId);
	    }
		
		Driver newDriver = driverRepo.findByPersonId(driverId);
		if (newDriver == null) {
	        throw new Exception("Driver not found for ID: " + driverId);
	    }
		
		parcelToChange.setDriver(newDriver);
		
		parcelRepo.save(parcelToChange);
	}

	@Override
	public float calculateIncomeOfParcelsByCustomerId(int customerId) throws Exception {
		if(customerId <= 0) throw new Exception("Customer ID should be positive");
		
		ArrayList<Parcel> parcelsForCustomer = parcelRepo.findByCustomerId(customerId);
		
		if (parcelsForCustomer == null || parcelsForCustomer.isEmpty()) {
	        throw new Exception("No parcels found for customer ID: " + customerId);
	    }

	    float totalIncome = 0.0f;
	    for (Parcel parcel : parcelsForCustomer) {
	        totalIncome += parcel.getPrice(); // Assuming getPrice() method exists in Parcel class
	    }
	    
	    return totalIncome;
		
	}

	@Override
	public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		LocalDateTime todayStart = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
	    LocalDateTime todayEnd = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
	    
	    ArrayList<Parcel> parcelsToBeDeliveredToday = parcelRepo.findByPlannedDeliveryBetween(todayStart, todayEnd);
	    
	    if (parcelsToBeDeliveredToday == null)
	        throw new Exception("Parcel repository is empty or null");
	    
	    int parcelsToDeliverTodayCount = parcelsToBeDeliveredToday.size();
	    
	    return parcelsToDeliverTodayCount;
	}

}
