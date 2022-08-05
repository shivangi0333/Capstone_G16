package com.wipro.capstoneshopfrohome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.Admin;


@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long>{

	public boolean existsByEmail(String email);
	
	public Admin findByEmail(String email);
	
}