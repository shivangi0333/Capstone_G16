package com.greatlearning.Discount.repository;

import com.greatlearning.Discount.bean.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
