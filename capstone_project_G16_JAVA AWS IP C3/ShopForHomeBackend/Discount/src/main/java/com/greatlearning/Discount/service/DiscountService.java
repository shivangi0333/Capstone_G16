package com.greatlearning.Discount.service;

import java.util.List;

import com.greatlearning.Discount.bean.Discount;
import com.greatlearning.Discount.repository.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	
	@Autowired
	DiscountRepository discountRepository;
	
	public List<Discount> getAllDiscount(){
		 return  discountRepository.findAll();
	}
	
	public String storeDiscount(Discount discount)
	{
		if(discount.getDpercent()<1 && discount.getDpercent()>95)
		{
			return "discount should be in the range 1 to 95";
		}
		else
		{
			discountRepository.save(discount);
			return "discount coupon added";
		}
	}

	public String removeDiscount(int did) {
		// TODO Auto-generated method stub
		discountRepository.deleteById(did);
		return "deleted";
	}

}
