package com.webstore.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webstore.shoppingcart.domain.User;
import com.webstore.shoppingcart.persistence.UserRepository;

@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User user = new User();
		user.setName("Ewerson");
		user.setEMail("ewersoncarmo@gmail.com");
		
		userRepository.save(user);
		
		User user2 = new User();
		user2.setName("Silvia");
		user2.setEMail("ewersoncarmo@gmail.com");
		
		userRepository.save(user2);
	}

}
