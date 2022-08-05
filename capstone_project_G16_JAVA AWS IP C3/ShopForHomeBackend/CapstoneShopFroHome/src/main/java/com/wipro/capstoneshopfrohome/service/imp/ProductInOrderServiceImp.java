package com.wipro.capstoneshopfrohome.service.imp;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.repository.IProductInOrderRepository;
import com.wipro.capstoneshopfrohome.service.IProductInOrderService;

import lombok.var;

@Service
public class ProductInOrderServiceImp implements IProductInOrderService {

	@Autowired
	IProductInOrderRepository productInOrderRepo;
	
	
	@Override
	@Transactional
	public void update(String itemId, Integer quantity, User user) {
		// TODO Auto-generated method stub

		var op = user.getCart().getProducts().stream().filter(e -> itemId.contentEquals(e.getProductId())).findFirst();
		op.ifPresent(productInOrder -> {
			productInOrder.setCount(quantity);
			productInOrderRepo.save(productInOrder);
		});
	}

	@Override
	public ProductInOrder findOne(String itemId, User user) {
		// TODO Auto-generated method stub
		var op=user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
		AtomicReference<ProductInOrder> res = new AtomicReference<>();
		op.ifPresent(res::set);
		return res.get();
	}

}
