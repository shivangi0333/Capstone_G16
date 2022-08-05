package com.wipro.capstoneshopfrohome.service;

import java.util.List;

import com.wipro.capstoneshopfrohome.entity.SalesLog;

public interface ISalesLogService {

	public SalesLog addSales(SalesLog sales);
	
	public List<SalesLog> getSales();
}
