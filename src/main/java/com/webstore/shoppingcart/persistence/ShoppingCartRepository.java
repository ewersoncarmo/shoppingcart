package com.webstore.shoppingcart.persistence;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webstore.shoppingcart.domain.entity.ShoppingCart;
import com.webstore.shoppingcart.domain.enumerator.PurchaseStatus;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

	Optional<ShoppingCart> findByUser_IdAndPurchaseStatus(String id, PurchaseStatus purchaseStatus);

	Optional<ShoppingCart> findByUser_Id(String userId);

}
