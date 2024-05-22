package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Integer>{

	Driver findByNameAndSurnameAndPersonCodeAndLicenseNoAndExperienceInYears(String name, String surname,
			String personCode, String licenseNo, float experienceInYears);

}
