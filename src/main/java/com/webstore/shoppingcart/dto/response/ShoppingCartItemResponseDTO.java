package com.webstore.shoppingcart.dto.response;

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
public class ShoppingCartItemResponseDTO {

	private ItemResponseDTO item;
	private Integer quantity;
	private BigDecimal totalAmount;
}
