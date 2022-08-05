package com.greatlearning.Discount.controller;

import java.util.List;

import com.greatlearning.Discount.bean.Discount;
import com.greatlearning.Discount.service.DiscountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
@CrossOrigin(origins = "http://localhost:4200")
public class DiscountController {
	
	@Autowired
	DiscountService discountService;
	
	@GetMapping("/discountName")
	public List<Discount> getAllDiscount(){
		return  discountService.getAllDiscount();
	}
	
	@PostMapping("/add")
	public String addDiscount(@RequestBody Discount discount)
	{
		System.out.println(discount.getDname());
		return discountService.storeDiscount(discount);
	}

	@DeleteMapping("/delete/{dname}")
	public String deleteDiscount(@PathVariable int dname)
	{
		return discountService.removeDiscount(dname);
	}
}
