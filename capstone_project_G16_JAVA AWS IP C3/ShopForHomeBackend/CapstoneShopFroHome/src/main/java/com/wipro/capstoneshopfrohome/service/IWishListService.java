package com.wipro.capstoneshopfrohome.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.entity.WishList;


public interface IWishListService {

	Boolean deleteWishlist(User user, String productId);

	Page<WishList> findByBuyerEmail(Long id, PageRequest request);

	WishList createWishlist(WishList wishList);
	
	public void deleteWishlistByUser(Long userId);
}
