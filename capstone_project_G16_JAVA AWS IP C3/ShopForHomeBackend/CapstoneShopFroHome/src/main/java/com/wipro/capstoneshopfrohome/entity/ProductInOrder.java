package com.wipro.capstoneshopfrohome.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ProductInOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Cart cart;
	
	
	private String productId;
	
	private String productName;
	
	private String productDescription;
	
	private String productUrl;
	
	private Integer categoryType;
	
	private BigDecimal productPrice;
	
	private Integer productStock;
	
	private Integer count;
	
	public ProductInOrder() {

	}

	public ProductInOrder(Product productInfo, Integer quantity) {
		this.productId = productInfo.getProductId();
		this.productName = productInfo.getProductName();
		this.productDescription = productInfo.getProductDescription();
		this.productUrl = productInfo.getProductUrl();
		this.categoryType = productInfo.getCategoryType();
		this.productPrice = productInfo.getProductPrice();
		this.productStock = productInfo.getProductStock();
		this.count = quantity;
	}

	@Override
	public String toString() {
		return "ProductInOrder{" + "id=" + id + ", productId='" + productId + '\'' + ", productName='" + productName
				+ '\'' + ", productDescription='" + productDescription + '\'' + ", productIcon='" + productUrl + '\''
				+ ", categoryType=" + categoryType + ", productPrice=" + productPrice + ", productStock=" + productStock
				+ ", count=" + count + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		ProductInOrder that = (ProductInOrder) o;
		return Objects.equals(id, that.id) && Objects.equals(productId, that.productId)
				&& Objects.equals(productName, that.productName)
				&& Objects.equals(productDescription, that.productDescription)
				&& Objects.equals(productUrl, that.productUrl) && Objects.equals(categoryType, that.categoryType)
				&& Objects.equals(productPrice, that.productPrice);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productUrl, categoryType,
				productPrice);
	}

	
	
}
