package com.wipro.capstoneshopfrohome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CapstoneShopFroHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneShopFroHomeApplication.class, args);
	}

	@Bean
	public RestTemplate  getRestTemplate() {
		
		return new RestTemplate();
		
	}

}
