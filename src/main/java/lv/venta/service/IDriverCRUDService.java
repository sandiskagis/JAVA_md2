package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Driver;

public interface IDriverCRUDService {

	
	public abstract ArrayList<Driver> selectAllDriver() throws Exception;
	
	public abstract Driver selectDriverById(int id) throws Exception;
	
	public abstract void deleteDriverById(int id) throws Exception;
	
	public abstract Driver insertNewDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception;
	
	public abstract void updateDriverById(int id, String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception;
	
	
}
