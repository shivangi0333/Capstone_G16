package com.wipro.capstoneshopfrohome.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.capstoneshopfrohome.entity.Admin;
import com.wipro.capstoneshopfrohome.repository.IAdminRepository;
import com.wipro.capstoneshopfrohome.service.IAdminService;
import com.wipro.capstoneshopfrohome.vo.Discount;

@Service
public class AdminServiceImp implements IAdminService {

	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	RestTemplate restTemplate;


	@Override
	public String checkAdminDetails(Admin admin) {
		if (adminRepo.existsByEmail(admin.getEmail())) {
			Admin a = adminRepo.findByEmail(admin.getEmail());
			if (a.getPassword().equals(admin.getPassword())) {
				return "You Logged In sucessfully";
			} else {
				return "Please Enter Correct Details";
			}
		} else {
			return "Your Details Are Not Exists!!";
		}

	}
	@Override
	public String adminLogout(String email) {
		if (adminRepo.existsByEmail(email)) {
			return " Admin LogOut Sucessfully";
		} else {
			return "Please Enter Correct Details";
		}
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}
	
	@Override
	public Discount[] getAllDiscounts() {
		// TODO Auto-generated method stub
		ResponseEntity<Discount[]>  response 
		 =  restTemplate.getForEntity("http://localhost:9124/discount/discountName", Discount[].class);
		return response.getBody();
		
	}
	
	@Override
	public String addDiscountCoupon(Discount discount) {
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9124/discount/add",discount,String.class);
		return response.getBody();
	}
	@Override
	public String deleteCoupon(int dname) {
		// TODO Auto-generated method stub
		Map < String, Integer > params = new HashMap < String, Integer> ();
        params.put("dname",dname);
		restTemplate.delete("http://localhost:9124/discount/delete/",params);
		return "deleted";
	}
	
	

	   
}
