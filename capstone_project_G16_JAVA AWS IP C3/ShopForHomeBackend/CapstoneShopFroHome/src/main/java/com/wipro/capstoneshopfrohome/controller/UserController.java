package com.wipro.capstoneshopfrohome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.service.IUserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	IUserService userService;

	@PostMapping("/register")
	public String storUserDetails(@RequestBody User user)
	{
		return userService.storeUserDetails(user);
	}
	
	@PostMapping("/login")
	public String checkUserInfo(@RequestBody User user) {
		return userService.checkUserDetails(user);	
	}
	
	@GetMapping(value="displayall")
	public List<User> getAllDetailsOfUsers(){
		return userService.getAllUsersDetails();
	}
	
	@GetMapping("/display/{email}")
	public User getUser(@PathVariable String email) {
		return userService.findOne(email);
	}
	
	@DeleteMapping("/deleteuser/{email}")
	public String deleteUserDetails(@PathVariable String email) {
		return userService.deleteUserDetails(email);
	}
	
	@PutMapping("/updateUser")
	public String updateUserPassword(@RequestBody User user) {
		return userService.updateUserDetails(user);
	}
	
}
