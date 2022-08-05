package com.wipro.capstoneshopfrohome.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.capstoneshopfrohome.entity.Category;
import com.wipro.capstoneshopfrohome.repository.ICategoryRepository;
import com.wipro.capstoneshopfrohome.service.ICategoryService;

@Service
public class CategoryServiceImp implements ICategoryService {

	@Autowired
	ICategoryRepository categoryRepo;
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return categoryRepo.save(category);
	}

}
