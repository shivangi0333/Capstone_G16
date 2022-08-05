package com.wipro.capstoneshopfrohome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.Product;
import com.wipro.capstoneshopfrohome.service.IProductService;


@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@GetMapping("/getAllProduct")
	public List<Product> getAllProductInfo() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/storeProduct")
	public String storeProduct(@RequestBody Product product) {
		return productService.storeProduct(product);
	}

	@DeleteMapping("/deleteProduct/{pid}")
	public String removeProduct(@PathVariable("pid") String pid) {
		return productService.deleteProduct(pid);
	}

	@PutMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product) {
		System.out.println(product.getProductId());
		return productService.updateProduct(product);
	}
		
}
