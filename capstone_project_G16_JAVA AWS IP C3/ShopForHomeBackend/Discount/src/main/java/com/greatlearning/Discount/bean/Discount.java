package com.greatlearning.Discount.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int did;
	private String dname;
	private int dpercent;
	
	public Discount() {
		super();
		//TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Discount [did=" + did + ", dname=" + dname + ", dpercent=" + dpercent + "]";
	}



	public Discount(int did, String dname, int dpercent) {
		super();
		this.did = did;
		this.dname = dname;
		this.dpercent = dpercent;
	}



	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getDpercent() {
		return dpercent;
	}

	public void setDpercent(int dpercent) {
		this.dpercent = dpercent;
	}
	
	

}
