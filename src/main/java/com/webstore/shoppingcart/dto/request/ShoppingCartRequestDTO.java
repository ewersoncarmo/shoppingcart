package com.webstore.shoppingcart.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartRequestDTO {

	@NotNull
	private List<ShoppingCartItemRequestDTO> items;
}
