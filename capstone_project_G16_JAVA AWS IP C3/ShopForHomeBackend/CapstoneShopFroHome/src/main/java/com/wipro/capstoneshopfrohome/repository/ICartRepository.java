package com.wipro.capstoneshopfrohome.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.Cart;


@Repository
public interface ICartRepository extends JpaRepository<Cart,Long>{
	
	public Cart findByUserId(long userId);
	
}
