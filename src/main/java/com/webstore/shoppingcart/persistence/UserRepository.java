package com.webstore.shoppingcart.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.User;

public interface UserRepository extends MongoRepository<User, Long> {

	
}
