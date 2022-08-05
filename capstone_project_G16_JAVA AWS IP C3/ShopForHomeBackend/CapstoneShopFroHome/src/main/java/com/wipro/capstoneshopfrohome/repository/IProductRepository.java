package com.wipro.capstoneshopfrohome.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product,String>{
	
	Product findByProductId(String id);
}