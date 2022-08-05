package com.wipro.capstoneshopfrohome.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.capstoneshopfrohome.entity.Product;


public class CSVHelper {
	
	public static String type = "text/csv";
    static String[] Headers = {"productId","productName","productPrice","productStock","productDescription","productUrl","categoryType"};
	
	public static boolean hasCSVFormat(MultipartFile file) {
		System.out.println("2");
		if(type.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			System.out.println("3");
			return true;
		}
		System.out.println("4");
		return false;
	}
	
	public static List<Product> csvToProduct(InputStream is){
		try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				@SuppressWarnings("deprecation")
				CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
			List<Product> productList = new ArrayList<>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for(CSVRecord csvRecord : csvRecords) {;
				Product product = new Product();
				product.setProductId(csvRecord.get("productId"));
				product.setProductName(csvRecord.get("productName"));
				product.setProductPrice(BigDecimal.valueOf(Double.parseDouble(csvRecord.get("productPrice"))));
				product.setProductStock(Integer.parseInt(csvRecord.get("productStock")));
				product.setProductDescription(csvRecord.get("productDescription"));
				product.setProductUrl(csvRecord.get("productUrl"));
				product.setCategoryType(Integer.parseInt(csvRecord.get("categoryType")));
				productList.add(product);
			}
			return productList;
			
		}
		catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
	}

}
