package lv.venta.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.ParcelSize;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.service.IParcelService;

@Controller
@RequestMapping("/parcel")
public class ParcelController {

	@Autowired
	private IParcelService parcelService;
	
	@Autowired
	ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	ICustomerAsPersonRepo customerAsPersonRepo;
	
	@GetMapping("/show/customer/{id}") // localhost:8080/parcel/show/customer/2
	public String getParcelShowByCustomerId(@RequestParam("id") int id, Model model) {
		
		try
		{
			model.addAttribute("mydata", parcelService.selectAllParcelsByCustomerId(id));
			return "parcel-all-page";// tiek parādīta parcel-all-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	@GetMapping("/show/driver/{id}") // localhost:8080/parcel/show/driver/2
	public String getParcelShowByDriverId(@RequestParam("id") int id, Model model) {
		
		try
		{
			model.addAttribute("mydata", parcelService.selectAllParcelsDeliveredByDriverId(id));
			return "parcel-all-page";// tiek parādīta parcel-all-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	@GetMapping("/show/price/{threshold}") // localhost:8080/parcel/show/price/5
	public String getParcelFilterByPrice(@PathVariable("threshold") float threshold, Model model) {
		
		try
		{
			ArrayList<Parcel> filterParcels 
			= parcelService.selectAllParcelsPriceLessThan(threshold);
			model.addAttribute("mydata", filterParcels);
			model.addAttribute("msg", "Parcels filtered by price: " + threshold + " eur");
			return "parcel-all-page";// tiek parādīta parcel-all-page.html lapa

		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	@GetMapping("/show/city/{cityparam}") // localhost:8080/parcel/show/city/5
	public String getParcelFilterByPrice(@PathVariable("cityparam") City city, Model model) {
		
		try
		{
			ArrayList<Parcel> filterParcels 
			= parcelService.selectAllParcelsDeliveredToCity(city);
			model.addAttribute("mydata", filterParcels);
			model.addAttribute("msg", "Parcels filtered by city: " + city);
			return "parcel-all-page";// tiek parādīta parcel-all-page.html lapa

		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	@GetMapping("/change/{parcelid}/{driverid}") // localhost:8080/parcel/change/2/3
	public String getParcelChangeDriverById(@PathVariable("parcelid") int parcelId, @PathVariable("driverid") int driverId, Model model) {
		
		try
		{
			model.addAttribute("mydata", parcelService.changeParcelDriverByParcelIdAndDriverId(parcelId, driverId));
			return "parcel-all-page";// tiek parādīta parcel-all-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	
	//todo continue
	
	
	
	
	
}
