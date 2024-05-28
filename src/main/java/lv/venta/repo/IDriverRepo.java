package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Integer>{


	boolean existsById(int id);

	Driver findByPersonCode(String personCode);

	Driver findByPersonId(int id);

}
