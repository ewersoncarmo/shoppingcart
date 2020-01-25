package com.webstore.shoppingcart.domain.entity;

import java.math.BigDecimal;

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
public class ShoppingCartItem {

	private Item item;
	private Integer quantity;
	private BigDecimal totalAmount;
}
