package com.wipro.capstoneshopfrohome.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.capstoneshopfrohome.entity.User;

@Repository
public class WishListCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Boolean deleteWishlist(User user, String productId) {
		
		getSession().createNativeQuery("delete from super.wish_list where product_id=:productId and user_id=:userId")
				.setParameter("productId", productId).setParameter("userId", user.getId()).executeUpdate();
		return true;
	}
	
	@Transactional
	public Boolean deleteWishlistUserid(User user) {
		
		getSession().createNativeQuery("delete from super.wish_list where user_id=:userId")
				.setParameter("userId", user.getId()).executeUpdate();
		return true;
	}

	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
