package com.webstore.shoppingcart.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByName(String name);
	
}
