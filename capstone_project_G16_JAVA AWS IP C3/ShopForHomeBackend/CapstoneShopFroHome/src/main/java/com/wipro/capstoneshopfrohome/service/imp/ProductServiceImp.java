package com.wipro.capstoneshopfrohome.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.capstoneshopfrohome.entity.Product;
import com.wipro.capstoneshopfrohome.repository.IProductRepository;
import com.wipro.capstoneshopfrohome.service.ICategoryService;
import com.wipro.capstoneshopfrohome.service.IProductService;

@Service
public class ProductServiceImp implements IProductService {

	@Autowired
	IProductRepository productRepo;
	
	@Autowired
	ICategoryService categoryService;
	
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product findOne(String productId) {
		// TODO Auto-generated method stub
		Product product = productRepo.findByProductId(productId);
		return product;
	}


	@Override
	@Transactional
	public void increaseStock(String productId, int amount) {
		// TODO Auto-generated method stub
		Product product = findOne(productId);
		int update = product.getProductStock() + amount;
		product.setProductStock(update);
		productRepo.save(product);
	}

	@Override
	public void decreaseStock(String productId, int amount) {
		// TODO Auto-generated method stub
		Product product = findOne(productId);
		int update = product.getProductStock() - amount;
		if(update>0)
		{
			product.setProductStock(update);
			productRepo.save(product);
		}
	}

	@Override
	public String storeProduct(Product product) {
		productRepo.save(product);
		return "Product Stored Successfully";
	}
	
	//update
	@Override
	public String updateProduct(Product product) {
			productRepo.saveAndFlush(product);
			return "Product Price Updated Successfully";
	}
	
	//delete
	@Override
	public String deleteProduct(String pid) {
		if (!productRepo.existsById(pid)) {
			return "Product  Details Not Present";
		} else {
			productRepo.deleteById(pid);
			return "Product Deleted Successfully";
		}
	}
	

}
