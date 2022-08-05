package com.wipro.capstoneshopfrohome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.ProductInOrder;

@Repository
public interface IProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}