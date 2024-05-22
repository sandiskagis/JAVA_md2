package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Parcel;


public interface IParcelRepo extends CrudRepository<Parcel, Integer>{

	ArrayList<Parcel> findByCustomerId(int id);

}
