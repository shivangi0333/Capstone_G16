package com.wipro.capstoneshopfrohome.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cart")
	private Set<ProductInOrder> products = new HashSet<>();

	public Cart() {
		
	}

	public Cart(User user) {
		this.user = user;
	}
	
}
