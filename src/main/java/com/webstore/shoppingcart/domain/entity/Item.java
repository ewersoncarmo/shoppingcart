package com.webstore.shoppingcart.domain.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

	@Id
	private String id;
	private String name;
	private BigDecimal amount;

}
