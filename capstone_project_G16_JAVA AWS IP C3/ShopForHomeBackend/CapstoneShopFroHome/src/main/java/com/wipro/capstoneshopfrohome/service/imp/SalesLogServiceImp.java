package com.wipro.capstoneshopfrohome.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.capstoneshopfrohome.entity.SalesLog;
import com.wipro.capstoneshopfrohome.repository.ISalesLogRepository;
import com.wipro.capstoneshopfrohome.service.ISalesLogService;

@Service
public class SalesLogServiceImp implements ISalesLogService {

	@Autowired
	ISalesLogRepository salesRepo;
	
	@Override
	public SalesLog addSales(SalesLog sales)
	{
		return salesRepo.save(sales);
	}
	
	@Override
	public List<SalesLog> getSales()
	{
		return salesRepo.findAll();
	}
}
