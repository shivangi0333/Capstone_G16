package com.wipro.capstoneshopfrohome.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.WishList;


@Repository
public interface IWishListRepository extends JpaRepository<WishList, Long>{

	Page<WishList> findAllByUserId(Long id, Pageable pageable);
	
	public void deleteByUserId(Long id);
	
	public boolean existsByUserId(Long id);
}
