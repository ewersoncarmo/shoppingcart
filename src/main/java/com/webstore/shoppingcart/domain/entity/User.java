package com.webstore.shoppingcart.domain.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

	@Id
	private String id;
	private String name;
	private String email;
	
}
