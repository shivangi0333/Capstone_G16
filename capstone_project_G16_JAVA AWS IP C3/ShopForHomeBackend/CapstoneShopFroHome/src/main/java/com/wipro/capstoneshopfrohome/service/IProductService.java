package com.wipro.capstoneshopfrohome.service;

import java.util.List;


import com.wipro.capstoneshopfrohome.entity.Product;



public interface IProductService {
	
	public List<Product> getAllProducts();

	public Product findOne(String productId);

	void increaseStock(String productId, int amount);

	void decreaseStock(String productId, int amount);

	public String deleteProduct(String pid);
	
	public String updateProduct(Product product);
	
	public String storeProduct(Product product);
	
}