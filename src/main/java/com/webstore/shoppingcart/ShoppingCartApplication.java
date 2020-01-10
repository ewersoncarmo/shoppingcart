package com.webstore.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webstore.shoppingcart.domain.User;
import com.webstore.shoppingcart.persistence.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}
