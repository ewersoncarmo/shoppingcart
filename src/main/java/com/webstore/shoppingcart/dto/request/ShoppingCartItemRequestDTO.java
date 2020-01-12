package com.webstore.shoppingcart.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartItemRequestDTO {

	@NotNull
	@NotBlank
	private String itemId;
	
	@NotNull
	@NotBlank
	@Min(value = 1)
	private Integer quantity;
}
