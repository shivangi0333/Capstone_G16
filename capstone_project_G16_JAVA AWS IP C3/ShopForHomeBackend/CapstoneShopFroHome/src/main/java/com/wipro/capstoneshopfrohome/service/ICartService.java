package com.wipro.capstoneshopfrohome.service;

import java.util.Collection;

import com.wipro.capstoneshopfrohome.entity.Cart;
import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.User;


public interface ICartService {

	public Cart getCart(User user);
	
	public void delete(String itemId,User user);
	
	public void mergeLocalCart(Collection<ProductInOrder> productInOrders,User user);
	
}