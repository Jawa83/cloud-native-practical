package com.ezgroceries.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class ShoppingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListApplication.class, args);
	}

}
