package com.webstore.shoppingcart.domain.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document
public class Item {

	@Id
	private String id;
	private String name;
	private BigDecimal amount;

}
