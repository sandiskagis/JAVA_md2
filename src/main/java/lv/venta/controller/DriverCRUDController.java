package lv.venta.controller;

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
import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;

@Controller
@RequestMapping("/driver")
public class DriverCRUDController {

	@Autowired
	private IDriverCRUDService driverService;
	
	@GetMapping("/all") // localhost:8080/product/all
	public String getDriverAll(Model model) {

		try {
			model.addAttribute("mydata", driverService.selectAllDriver());
			model.addAttribute("msg", "All drivers");
			return "driver-all-show-page";// tiek parādīta driver-all-show-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
	}
	
	@GetMapping("/one") // localhost:8080/driver/one?id=2
	public String getDriverOneId(@RequestParam("id") int id, Model model) {
		try
		{
			model.addAttribute("mydata", driverService.selectDriverById(id));
			return "driver-one-show-page";// tiek parādīta driver-one-show-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}

	}
	
	@GetMapping("/delete/{id}")//localhost:8080/driver/delete/2
	public String getProductDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			driverService.deleteDriverById(id);
			model.addAttribute("mydata", driverService.selectAllDriver());
			model.addAttribute("msg", "All drivers");
			return "driver-all-show-page";// tiek parādīta driver-all-show-page.html lapa
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
	}
	
	@GetMapping("/insert") //localhost:8080/driver/insert
	public String postProductInsert(Model model) {
		model.addAttribute("driver", new Driver());//noklusējuma driver tiks padots uz lapu
		return "driver-insert-page";//tiek parādīta driver-insert-page.html lapa
	}
	
	@PostMapping("/insert")
	public String postProductInsert(@Valid Driver driver, BindingResult result) {//iegūstam aju aizpildītu produktu
		//sajā gadījumā ir validāciju pāŗkāpumi Product objektam
		if(result.hasErrors()) {
			return "product-insert-page";//paliekam šajā pašā lapā
		}
		else
		{
			try {
				driverService.insertNewDriver(driver.getName(), driver.getSurname(), driver.getPersonCode(), driver.getLicenseNo(), driver.getExperienceInYears());
				return "redirect:/driver/all";//tiks pārvirzīts jeb izsaukts localhost:8080/driver/all
			} catch (Exception e) {
			
				return "redirect:/error";//tiks pārvirzīts jeb izsaukt loclahost:8080/error
			}
		}
	
	}
	
	@GetMapping("/update")//localhost:8080/driver/update?id=2
	public String getDriverUpdateById(@RequestParam("id") int id, Model model) {
		
		try {
			Driver updatedDriver = driverService.selectDriverById(id);
			model.addAttribute("driver", updatedDriver);
			model.addAttribute("id", id);
			return "driver-update-page";//tiks parādīta driver-update-page.html lapa ar atrasto produktu
			
			
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
	
	@PostMapping("/update")
	public String postDriverUpdateById(@Valid Driver driver, 
			BindingResult result, @RequestParam("id") int id, Model model) {
		
	if(result.hasErrors()) {
		model.addAttribute("id", id);
		return "driver-update-page";//tiks parādīta driver-update-page.html lapa ar atrasto produktu	
	}
	else
	{
		try
		{
			driverService.updateDriverById(id, driver.getName(), driver.getSurname(), driver.getPersonCode(), driver.getLicenseNo(), driver.getExperienceInYears());
			return "redirect:/driver/all"; //var arī "redirect:/driver/all/" + id; vai arī "redirect:/driver/one?id=" + id;
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";// tiek parādīta error-page.html lapa
		}
		
	}
		
		
	}
	
	
	
	
	
	
}
