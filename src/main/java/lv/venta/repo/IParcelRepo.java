package lv.venta.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;


public interface IParcelRepo extends CrudRepository<Parcel, Integer>{

	ArrayList<Parcel> findByCustomerId(int id);

	ArrayList<Parcel> findByDriverId(int id);

	ArrayList<Parcel> findByPriceLessThan(float price);

	ArrayList<Parcel> findByCity(City city);

	Parcel findByParcelId(int parcelId);

	ArrayList<Parcel> findByPlannedDeliveryBetween(LocalDateTime todayStart, LocalDateTime todayEnd);

}
