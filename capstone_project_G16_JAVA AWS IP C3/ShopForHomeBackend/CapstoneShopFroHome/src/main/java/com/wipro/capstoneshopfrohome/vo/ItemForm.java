package com.wipro.capstoneshopfrohome.vo;


import lombok.Data;

@Data
public class ItemForm {

	private Integer quantity;
	private String productId;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
