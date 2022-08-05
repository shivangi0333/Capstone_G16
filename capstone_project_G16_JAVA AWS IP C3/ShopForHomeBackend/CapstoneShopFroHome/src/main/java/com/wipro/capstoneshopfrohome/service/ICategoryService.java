package com.wipro.capstoneshopfrohome.service;

import java.util.List;

import com.wipro.capstoneshopfrohome.entity.Category;


public interface ICategoryService {
	
	public List<Category> findAll();

	public Category save(Category category);
	

}
