package com.webstore.shoppingcart.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

	@Id
	private String id;
	private String name;
	private String eMail;
	private Set<Item> items;
	
}
