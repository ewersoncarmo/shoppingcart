package com.webstore.shoppingcart.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemRequestDTO {

	@NotNull
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotNull
	@Digits(integer = 18, fraction = 2)
	@Positive
	private BigDecimal amount;
}
