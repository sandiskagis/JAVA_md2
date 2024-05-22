package lv.venta.service;

import lv.venta.model.Parcel;

import java.util.ArrayList;

public interface IParcelService {
	
	public abstract ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception; 
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredByDriverId() throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsPriceLessThan() throws Exception;
	
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredToCity() throws Exception;
	
	public abstract Parcel insertNewParcelByCustomerCodeAndDriverId() throws Exception;
	
	public abstract void changeParcelDriverByParcelIdAndDriverId() throws Exception;
	
	public abstract void calculateIncomeOfParcelsByCustomerId() throws Exception;
	
	public abstract void calculateHowManyParcelsNeedToDeliverToday() throws Exception;

}
