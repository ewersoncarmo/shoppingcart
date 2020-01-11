package com.webstore.shoppingcart.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {

	@NotNull
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	@Email
	private String eMail;
}
