package com.webstore.shoppingcart.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.entity.ShoppingCartItem;

public interface ShoppingCartItemRepository extends MongoRepository<ShoppingCartItem, String> {

	Optional<ShoppingCartItem> findByItem_Id(String itemId);

}
