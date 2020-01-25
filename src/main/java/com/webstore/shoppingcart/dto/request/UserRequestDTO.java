package com.webstore.shoppingcart.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRequestDTO {

	@NotNull
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
}
