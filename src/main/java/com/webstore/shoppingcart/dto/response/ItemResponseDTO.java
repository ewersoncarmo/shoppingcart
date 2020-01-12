package com.webstore.shoppingcart.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemResponseDTO {

	private String id;
	private String name;
	private BigDecimal amount;
}
