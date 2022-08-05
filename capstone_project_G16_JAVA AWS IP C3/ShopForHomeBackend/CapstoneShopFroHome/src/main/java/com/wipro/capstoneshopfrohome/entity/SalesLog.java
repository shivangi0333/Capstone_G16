package com.wipro.capstoneshopfrohome.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	private String userName;
	private String productId;
	private String productName;
	private Date date;
	private int quantity;
	private int discount;
	private BigDecimal totalPrice;
	private BigDecimal soldPrice;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal productPrice) {
		this.totalPrice = productPrice;
	}
	public BigDecimal getSoldPrice() {
		return soldPrice;
	}
	public void setSoldPrice(BigDecimal soldPrice) {
		this.soldPrice = soldPrice;
	}
	public SalesLog() {
		super();
	}
	public SalesLog(int sid, String userName, String productId, String productName, Date date, int quantity,
			int discount, BigDecimal totalPrice, BigDecimal soldPrice) {
		super();
		this.sid = sid;
		this.userName = userName;
		this.productId = productId;
		this.productName = productName;
		this.date = date;
		this.quantity = quantity;
		this.discount = discount;
		this.totalPrice = totalPrice;
		this.soldPrice = soldPrice;
	}

	
	
}
