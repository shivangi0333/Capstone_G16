package com.wipro.capstoneshopfrohome.service;

import java.util.List;


import com.wipro.capstoneshopfrohome.entity.Admin;
import com.wipro.capstoneshopfrohome.vo.Discount;


public interface IAdminService {

	public String checkAdminDetails(Admin admin);
	
	public String adminLogout(String email);
	
	public List<Admin> getAllAdmins();

	public Discount[] getAllDiscounts();

	public String addDiscountCoupon(Discount discount);

	public String deleteCoupon(int dname);
	
	
}
