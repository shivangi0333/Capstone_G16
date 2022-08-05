package com.wipro.capstoneshopfrohome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.User;


@Repository
public interface IUserRepository extends JpaRepository<User,Long>{
	
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email); 
	
	public void deleteByEmail(String email);
	
}

