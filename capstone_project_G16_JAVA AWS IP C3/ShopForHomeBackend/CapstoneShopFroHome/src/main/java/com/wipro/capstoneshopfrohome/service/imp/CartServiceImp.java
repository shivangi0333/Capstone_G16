package com.wipro.capstoneshopfrohome.service.imp;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.capstoneshopfrohome.entity.Cart;
import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.repository.ICartRepository;
import com.wipro.capstoneshopfrohome.repository.IProductInOrderRepository;
import com.wipro.capstoneshopfrohome.repository.IUserRepository;
import com.wipro.capstoneshopfrohome.service.ICartService;
import com.wipro.capstoneshopfrohome.service.IProductService;
import com.wipro.capstoneshopfrohome.service.IUserService;

import lombok.var;

@Service
public class CartServiceImp implements ICartService {

	@Autowired
	IProductService productService;
	
	@Autowired
	IUserService userService;
	
	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	IProductInOrderRepository productInOrderRepo; 
	
	@Autowired
	ICartRepository cartRepo;
	
	@Override
	public Cart getCart(User user) {
		// TODO Auto-generated method stub
		return user.getCart();
	}

	@Override
	@Transactional
	public void delete(String itemId, User user) {
		// TODO Auto-generated method stub
		var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
		op.ifPresent(productInOrder ->{
			productInOrder.setCart(null);
			productInOrderRepo.deleteById(productInOrder.getId());
		});
	}

	@Override
	@Transactional
	public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
		// TODO Auto-generated method stub
		Cart finalCart = user.getCart();
		productInOrders.forEach(productInOrder -> {
			Set<ProductInOrder> set=finalCart.getProducts();
			Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().contentEquals(productInOrder.getProductId())).findFirst();
			ProductInOrder prod;
			if(old.isPresent()) {
				prod=old.get();
				prod.setCount(productInOrder.getCount()+prod.getCount());
			} else {
				prod = productInOrder;
				prod.setCart(finalCart);
				finalCart.getProducts().add(prod);
			}
			productInOrderRepo.save(prod);
		});
		cartRepo.save(finalCart);
	}

}
