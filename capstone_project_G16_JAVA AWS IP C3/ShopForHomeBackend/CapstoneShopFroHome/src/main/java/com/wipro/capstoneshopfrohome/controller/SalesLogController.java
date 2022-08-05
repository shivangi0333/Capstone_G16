package com.wipro.capstoneshopfrohome.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.Cart;
import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.SalesLog;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.service.ICartService;
import com.wipro.capstoneshopfrohome.service.ISalesLogService;
import com.wipro.capstoneshopfrohome.service.IUserService;



@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesLogController {

	@Autowired
	ISalesLogService salesService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICartService cartService;
	
	@PostMapping("/add/{email}/{discount}")
	public Boolean storeSales(@PathVariable("email") String email,@PathVariable("discount") String discount)
	{
		int discount1=Integer.parseInt(discount);
		User user = userService.findOne(email);
		Cart cart = user.getCart();
		Collection<ProductInOrder> products=cart.getProducts();
		for( ProductInOrder product : products)
		{
			SalesLog sale=new SalesLog();
			sale.setProductId(product.getProductId());
			sale.setProductName(product.getProductName());
			sale.setDiscount(discount1);
			sale.setQuantity(product.getCount());
			sale.setTotalPrice(product.getProductPrice().multiply(BigDecimal.valueOf(product.getCount())));
			sale.setUserName(user.getName());
			BigDecimal temp=sale.getTotalPrice().multiply(BigDecimal.valueOf(discount1));
			BigDecimal temp1=temp.divide(BigDecimal.valueOf(100));
			sale.setSoldPrice(sale.getTotalPrice().subtract(temp1));
			sale.setDate(new Date());
			salesService.addSales(sale);
			cartService.delete(product.getProductId(),user);
			
		}
		return true;
	}
	
	@GetMapping("/getAll")
	public List<SalesLog> getAllSales()
	{
		return salesService.getSales();
	}
}
