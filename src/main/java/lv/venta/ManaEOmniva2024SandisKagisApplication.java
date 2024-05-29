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
				//Person p1 = new Person("Janis", "Berzins", "130599-43619");
				//Person p2 = new Person("Peteris", "Kalnins", "081288-89021");
				Person p1 = new Person("Pirmais", "Cilveks", "111111-11111");
				Person p2 = new Person("Otrias", "Cilveks", "222222-22222");
				Person p3 = new Person("Trešais", "Cilveks", "333333-33333");
				Person p4 = new Person("Ceturtais", "Cilveks", "444444-44444");
				Person p5 = new Person("Piektais", "Cilveks", "555555-55555");
				Person p6 = new Person("Sestais", "Cilveks", "666666-66666");
				personRepo.save(p1);
				personRepo.save(p2);
				personRepo.save(p3);
				personRepo.save(p4);
				personRepo.save(p5);
				personRepo.save(p6);
				
				//Driver d1 = new Driver("Maris", "Kviesis", "120300-23231", "12345678", 2.5f);
				//Driver d2 = new Driver("Ricards", "Smiltins", "151288-32889", "44448888", 5.6f);
				Driver d1 = new Driver("Pirmais", "Soferis", "121111-11111", "11111111", 1.1f);
				Driver d2 = new Driver("Otrias", "Soferis", "232222-22222", "22222222", 2.2f);
				Driver d3 = new Driver("Trešais", "Soferis", "343333-33333", "33333333", 3.3f);
				Driver d4 = new Driver("Ceturtais", "Soferis", "454444-44444", "44444444", 4.4f);
				Driver d5 = new Driver("Piektais", "Soferis", "565555-55555", "55555555", 5.5f);
				Driver d6 = new Driver("Sestais", "Soferis", "676666-66666", "66666666", 6.6f);
				driverRepo.save(d1);
				driverRepo.save(d2);
				driverRepo.save(d3);
				driverRepo.save(d4);
				driverRepo.save(d5);
				driverRepo.save(d6);
				
				Address a1 = new Address(City.Riga, "Bruninieku iela", 101);
				Address a2 = new Address(City.Ventspils, "Inzenieru iela", 50);
				Address a3 = new Address(City.Daugavpils, "Bruklenu iela", 77);
				Address a4 = new Address(City.Liepaja, "Liepu iela", 43);
				Address a5 = new Address(City.Riga, "Brivibas iela", 32);
				Address a6 = new Address(City.Ventspils, "Krasta iela", 88);
				addressRepo.save(a1);
				addressRepo.save(a2);
				addressRepo.save(a3);
				addressRepo.save(a4);
				addressRepo.save(a5);
				addressRepo.save(a6);
				
				CustomerAsPerson cp1 = new CustomerAsPerson(p2, a1, "20202020");
				CustomerAsPerson cp2 = new CustomerAsPerson(p1, a3, "24397343");
				CustomerAsPerson cp3 = new CustomerAsPerson(p4, a5, "24332922");
				custAsPersRepo.save(cp1);
				custAsPersRepo.save(cp2);
				custAsPersRepo.save(cp3);
				
				CustomerAsCompany cc1 = new CustomerAsCompany(a2, "21334346", "SIA Uznemums", "66445533");
				CustomerAsCompany cc2 = new CustomerAsCompany(a4, "22228888", "SIA Kantoris", "99009900");
				CustomerAsCompany cc3 = new CustomerAsCompany(a5, "24473721", "SIA Iestade", "77372777");
				custAsCompRepo.save(cc1);
				custAsCompRepo.save(cc2);
				custAsCompRepo.save(cc3);
				
				Parcel pa1 = new Parcel(true, ParcelSize.M, cp1, d1, LocalDateTime.now().plus(1, ChronoUnit.WEEKS));
				Parcel pa2 = new Parcel(false, ParcelSize.L, cc1, d2, LocalDateTime.now().plus(2, ChronoUnit.WEEKS));
				Parcel pa3 = new Parcel(true, ParcelSize.X, cp2, d3, LocalDateTime.now().plus(1, ChronoUnit.DAYS));
				Parcel pa4 = new Parcel(false, ParcelSize.S, cp3, d5, LocalDateTime.now().plus(2, ChronoUnit.DAYS));
				Parcel pa5 = new Parcel(true, ParcelSize.M, cc2, d6, LocalDateTime.now().plus(1, ChronoUnit.MONTHS));
				Parcel pa6 = new Parcel(false, ParcelSize.L, cc3, d4, LocalDateTime.now().plus(2, ChronoUnit.MONTHS));
				parcelRepo.save(pa1);
				parcelRepo.save(pa2);
				parcelRepo.save(pa3);
				parcelRepo.save(pa4);
				parcelRepo.save(pa5);
				parcelRepo.save(pa6);
				
				
				
				
			}
		};
	}

}
