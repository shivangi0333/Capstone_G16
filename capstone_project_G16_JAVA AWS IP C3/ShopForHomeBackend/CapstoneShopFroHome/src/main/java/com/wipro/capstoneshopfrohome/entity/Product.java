package com.wipro.capstoneshopfrohome.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate
public class Product {

	@Id
	private String productId;
	private String productName;
	private BigDecimal productPrice;
	private Integer productStock;
	private String productDescription;
	private String productUrl;
	
	
	@ColumnDefault("0")
	private Integer categoryType;

}