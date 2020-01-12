package com.webstore.shoppingcart.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.entity.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	Optional<Item> findByName(String name);

}
