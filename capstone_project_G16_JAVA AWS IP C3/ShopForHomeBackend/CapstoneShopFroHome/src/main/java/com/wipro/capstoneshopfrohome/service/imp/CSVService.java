package com.wipro.capstoneshopfrohome.service.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.capstoneshopfrohome.config.CSVHelper;
import com.wipro.capstoneshopfrohome.entity.Product;
import com.wipro.capstoneshopfrohome.entity.SalesLog;
import com.wipro.capstoneshopfrohome.repository.IProductRepository;



@Service
public class CSVService {
	
	@Autowired
	IProductRepository productRepo;
	
	public void save(MultipartFile file) {
		try {
			List<Product> products = CSVHelper.csvToProduct(file.getInputStream());
			productRepo.saveAll(products);
		}
		catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
	
	public void downloadSalesReport(PrintWriter printwriter,List<SalesLog> sales)
	{
	
		printwriter.write("Sid,userName,productId,productName,date,quantity,discount,totalPrice,soldprice\n");
		for(SalesLog sale : sales)
		{
			printwriter.write(sale.getSid()+","+sale.getUserName()+","+sale.getProductId()+","+sale.getProductName()+","+sale.getDate()+","+sale.getQuantity()+","+sale.getDiscount()+","+sale.getTotalPrice()+","+sale.getSoldPrice()+"\n");
		}
	}

}
