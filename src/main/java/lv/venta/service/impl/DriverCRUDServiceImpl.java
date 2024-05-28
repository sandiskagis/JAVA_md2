package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Driver;
import lv.venta.model.Person;
import lv.venta.repo.IDriverRepo;
import lv.venta.service.IDriverCRUDService;

import org.springframework.stereotype.Service;

@Service
public class DriverCRUDServiceImpl implements IDriverCRUDService {

	@Autowired
	IDriverRepo driverRepo;
	
	@Override
	public ArrayList<Driver> selectAllDriver() throws Exception {
		if (driverRepo.count()==0)
			throw new Exception("Driver list is empty");
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver selectDriverById(int id) throws Exception {
		if (id > 0) {
			if(driverRepo.existsById(id)) 
			{
				return driverRepo.findByPersonId(id);
			}
			else
				throw new Exception("Driver with " + id + " is not found");

		} else {
			throw new Exception("Id should be positive");
		}
	}
	

	@Override
	public void deleteDriverById(int id) throws Exception {
		Driver deleteDriver = selectDriverById(id);
		driverRepo.delete(deleteDriver);
	}

	@Override
	public Driver insertNewDriver(String name, String surname, String personCode, String licenseNo,
			float experienceInYears) throws Exception {
		// pārbaudam ienākošos parametrus
		if(name == null || surname == null || personCode == null || licenseNo == null || experienceInYears < 0.1f)
			throw new Exception("Problems with input parameters");
		
		// noskaidrojam, vai jau tāds driver neeksistē
		Driver foundDriver = driverRepo.findByPersonCode(personCode);
		if(foundDriver != null)
			throw new Exception("This driver already exists");
		
		// ja tāds driver neeksistē, tad izveidojam jaunu
		Driver newDriver = new Driver(name, surname, personCode, licenseNo, experienceInYears);
		driverRepo.save(newDriver);
		return newDriver;
	}

	@Override
	public void updateDriverById(int id, String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception {
		
		Driver updateDriver = selectDriverById(id);
		
		if(name != null)
			updateDriver.setName(name);
		if(surname != null)
			updateDriver.setSurname(surname);
		if(personCode != null)
			updateDriver.setPersonCode(personCode);
		if(licenseNo != null)
			updateDriver.setLicenseNo(licenseNo);
		if(experienceInYears >= 0.2)
			updateDriver.setExperienceInYears(experienceInYears);
		
		driverRepo.save(updateDriver);
		
	}

}
