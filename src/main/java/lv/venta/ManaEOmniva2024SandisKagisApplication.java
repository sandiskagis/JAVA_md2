package lv.venta;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;

import lv.venta.model.Address;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.ParcelSize;
import lv.venta.model.Person;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.repo.IPersonRepo;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class ManaEOmniva2024SandisKagisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManaEOmniva2024SandisKagisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner testDatabaseLayer(IPersonRepo personRepo, IParcelRepo parcelRepo, 
			IDriverRepo driverRepo, ICustomerAsPersonRepo custAsPersRepo, ICustomerAsCompanyRepo custAsCompRepo, IAddressRepo addressRepo)
	{
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Person p1 = new Person("Janis", "Berzins", "130599-43619");
				Person p2 = new Person("Peteris", "Kalnins", "081288-89021");
				personRepo.save(p1);
				personRepo.save(p2);
				
				Parcel pa1 = new Parcel(true, LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.WEEKS), 2.8f, ParcelSize.M);
				Parcel pa2 = new Parcel(false, LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.WEEKS), 7.2f, ParcelSize.XL);
				parcelRepo.save(pa1);
				parcelRepo.save(pa2);
				
				Driver d1 = new Driver("Maris", "Kviesis", "120300-23231", "12345678", 2.5f);
				Driver d2 = new Driver("Ricards", "Smiltins", "151288-32889", "44448888", 5.6f);
				driverRepo.save(d1);
				driverRepo.save(d2);
				
				Address a1 = new Address(City.Riga, "Bruninieku iela", 101);
				Address a2 = new Address(City.Ventspils, "Inzenieru iela", 50);
				Address a3 = new Address(City.Daugavpils, "Bruklenu iela", 77);
				Address a4 = new Address(City.Liepaja, "Liepu iela", 43);
				addressRepo.save(a1);
				addressRepo.save(a2);
				addressRepo.save(a3);
				addressRepo.save(a4);
				
				CustomerAsPerson cp1 = new CustomerAsPerson("Karlis", "Liepins", "210995-23876", a1, "20202020");
				CustomerAsPerson cp2 = new CustomerAsPerson("Andris", "Pakalns", "180695-54456", a2, "24397343");
				custAsPersRepo.save(cp1);
				custAsPersRepo.save(cp2);
				
				CustomerAsCompany cc1 = new CustomerAsCompany(a3, "21334346", "SIA Uznemums", "66445533");
				CustomerAsCompany cc2 = new CustomerAsCompany(a4, "22228888", "SIA Uznemums", "99009900");
				custAsCompRepo.save(cc1);
				custAsCompRepo.save(cc2);
			}
		};
	}

}
