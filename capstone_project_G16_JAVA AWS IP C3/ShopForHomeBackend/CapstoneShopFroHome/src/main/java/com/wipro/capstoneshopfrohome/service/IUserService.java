package com.wipro.capstoneshopfrohome.service;

import java.util.List;


import com.wipro.capstoneshopfrohome.entity.User;



public interface IUserService {
	
	public String storeUserDetails(User user);
	
	public String checkUserDetails(User user);
	
	public List<User> getAllUsersDetails();

	public User findOne(String email);

	
	public String deleteUserDetails(String email);
	
	public String updateUserDetails(User user);
	
}
