package com.webstore.shoppingcart.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.webstore.shoppingcart.domain.enumerator.PurchaseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ShoppingCart {

	@Id
	private String id;
	private PurchaseStatus purchaseStatus;
	private User user;
	private List<ShoppingCartItem> items;
	private BigDecimal totalAmount;

	public ShoppingCart(User user) {
		this.user = user;
		this.items = new ArrayList<>();
		this.purchaseStatus = PurchaseStatus.PENDING;
	}
}
