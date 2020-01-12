package com.webstore.shoppingcart.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.webstore.shoppingcart.domain.entity.User;

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
public class ShoppingCartResponseDTO {

	private String id;
	private User user;
	private String purchaseStatus;
	private List<ShoppingCartItemResponseDTO> items;
	private BigDecimal totalAmount;
}
