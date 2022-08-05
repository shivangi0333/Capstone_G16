package com.wipro.capstoneshopfrohome.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.capstoneshopfrohome.entity.Cart;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.repository.ICartRepository;
import com.wipro.capstoneshopfrohome.repository.IUserRepository;
import com.wipro.capstoneshopfrohome.repository.IWishListRepository;
import com.wipro.capstoneshopfrohome.repository.WishListCustomRepository;
import com.wipro.capstoneshopfrohome.service.IUserService;
import com.wipro.capstoneshopfrohome.service.IWishListService;

@Service
public class UserServiceImp implements IUserService {

	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	ICartRepository cartRepo;
	
	@Autowired
	IWishListRepository wishlistRepo;
	
	@Autowired
	IWishListService wishlistService;
	
	@Autowired
	WishListCustomRepository wishlistCustomRepo;
	
	@Override
	public String storeUserDetails(User user) {
		// TODO Auto-generated method stub
		try{
			if(!userRepo.existsByEmail(user.getEmail())) {
				User savedUser = userRepo.save(user);

				Cart savedCart = cartRepo.save(new Cart(savedUser));
				savedUser.setCart(savedCart);
				userRepo.save(savedUser);
				return "User Added Sucessfully";
			}else {
				return "User Already Exists!!!";
			}	
		}
		catch(Exception e){
			return "password length should be more than 7";
		}
	}

	@Override
	public String checkUserDetails(User user) {
		   if(userRepo.existsByEmail(user.getEmail())) {
			   User u = userRepo.findByEmail(user.getEmail());
			   if(u.getPassword().equals(user.getPassword())) {
				   return "You Logged In Sucessfully";
			   }else {
				   return "Please Enter Correct Details";
			   }				   
		   }else {
			   return "Your Details Are Not Exists!!";
		   }   
	   }
	
	@Override
	public List<User> getAllUsersDetails() {
		return userRepo.findAll();
	}
	
	
	@Override
	public User findOne(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}




	@Override
	@Transactional
	public String deleteUserDetails(String email) {
		User user=findOne(email);
		if(user==null) {
			return "User not Exists!!";
		}else {
			wishlistCustomRepo.deleteWishlistUserid(user);
			userRepo.deleteByEmail(email);
			return "User Deleted Sucessfully";
		}
	}
	
	@Override
	public String updateUserDetails(User user) {
		try{if(userRepo.existsByEmail(user.getEmail())) {
			User u=userRepo.findByEmail(user.getEmail());
			if(u.getPassword().equals(user.getPassword())) {
				return "You are giving old details";
			}else {
				u.setPassword(user.getPassword());
				userRepo.saveAndFlush(u);
				return "User Updated Sucessfully";
			}
		}else {
			return "User not Exists!!";			
		}	
		}
		catch(Exception e){
			return "password length should be more than 7";
		}
		
	}
	


}
