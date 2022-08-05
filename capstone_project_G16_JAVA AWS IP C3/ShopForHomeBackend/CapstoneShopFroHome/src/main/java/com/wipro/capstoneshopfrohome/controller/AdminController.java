package com.wipro.capstoneshopfrohome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.Admin;
import com.wipro.capstoneshopfrohome.service.IAdminService;
import com.wipro.capstoneshopfrohome.vo.Discount;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@PostMapping("/login")
	public String checkAdminInfo(@RequestBody Admin adm) {
		return adminService.checkAdminDetails(adm);	
	}
	
	@GetMapping("/display")
	public List<Admin> getAllAdminAvaliable() {
		return adminService.getAllAdmins();
	}
	
	
	 @GetMapping("/logout/{email}")
	 public String adminLogoutInfo(@PathVariable("email") String email) {
		 return adminService.adminLogout(email);
     }
	 
	 @GetMapping("/discount")
	 public Discount[] getDiscount() {
		 return adminService.getAllDiscounts();
	 }
	 
	 @PostMapping("/addDiscount")
	 public String addDiscount(@RequestBody Discount discount) {
		 return adminService.addDiscountCoupon(discount);
	 }
	 
	 @DeleteMapping("/deleteDiscount/{dname}")
	 public String deleteDiscount(@PathVariable int dname) {
		 return adminService.deleteCoupon(dname);
	 }
	 
}
