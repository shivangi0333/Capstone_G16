package com.wipro.capstoneshopfrohome.service;

import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.User;

public interface IProductInOrderService {
	
	public void update(String itemId, Integer quantity, User user);

	public ProductInOrder findOne(String itemId, User user);
}
