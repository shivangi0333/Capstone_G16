package com.wipro.capstoneshopfrohome.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Discount {
	private int did;
	private String dname;
	private int dpercent;
}
