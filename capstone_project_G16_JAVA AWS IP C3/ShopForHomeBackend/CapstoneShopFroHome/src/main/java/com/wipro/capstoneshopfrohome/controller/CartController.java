package com.wipro.capstoneshopfrohome.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.Cart;
import com.wipro.capstoneshopfrohome.entity.ProductInOrder;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.service.ICartService;
import com.wipro.capstoneshopfrohome.service.IProductInOrderService;
import com.wipro.capstoneshopfrohome.service.IProductService;
import com.wipro.capstoneshopfrohome.service.IUserService;
import com.wipro.capstoneshopfrohome.vo.ItemForm;

import lombok.var;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IProductInOrderService productInOrderService;
	
	@PostMapping("/merge/{email}")
	public ResponseEntity<Cart> mergeCart(@PathVariable String email,@RequestBody Collection<ProductInOrder> productInOrders) {
		User user = userService.findOne(email);
		try {
			cartService.mergeLocalCart(productInOrders, user);
		} catch (Exception e) {
			ResponseEntity.badRequest().body("Merge Cart Failed");
		}
		return ResponseEntity.ok(cartService.getCart(user));
	}

	@PostMapping("/add/{email}")
	public boolean addToCart(@PathVariable String email,@RequestBody ItemForm form) {
		var productInfo = productService.findOne(form.getProductId());
		try {
			mergeCart(email,Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@GetMapping("/get/{email}")
	public Cart getCart(@PathVariable String email) {
		User user = userService.findOne(email);
		return cartService.getCart(user);
	}
	
	@PutMapping("/modify/{itemId}/{email}")
	public ProductInOrder modifyItem(@PathVariable("itemId") String itemId,@PathVariable("email") String email, @RequestBody Integer quantity) {
		User user = userService.findOne(email);
		productInOrderService.update(itemId, quantity, user);
		return productInOrderService.findOne(itemId, user);
	}

	@DeleteMapping("/delete/{itemId}/{email}")
	public void deleteItem(@PathVariable("itemId") String itemId,@PathVariable("email") String email) {
		User user = userService.findOne(email);
		cartService.delete(itemId, user);
	}
	
	

}
