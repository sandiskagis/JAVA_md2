package lv.venta.service;

import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.ParcelSize;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IParcelService {
	
	public abstract ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception; 
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception;
	
	public abstract void insertNewParcelByCustomerCodeAndDriverId(String customerCode, int driverId, boolean isFragile, LocalDateTime orderCreated, LocalDateTime plannedDelivery, float price, ParcelSize size, CustomerAsPerson customerP, CustomerAsCompany customerC, Driver driver) throws Exception;
	
	public abstract Parcel changeParcelDriverByParcelIdAndDriverId(int parcelId, int driverId) throws Exception;
	
	public abstract float calculateIncomeOfParcelsByCustomerId(int customerId) throws Exception;
	
	public abstract int calculateHowManyParcelsNeedToDeliverToday() throws Exception;

}
