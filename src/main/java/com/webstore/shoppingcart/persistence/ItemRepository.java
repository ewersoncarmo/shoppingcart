package com.webstore.shoppingcart.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.Item;

public interface ItemRepository extends MongoRepository<Item, Long> {

}
