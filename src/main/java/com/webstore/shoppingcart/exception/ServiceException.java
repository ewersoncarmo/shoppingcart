package com.webstore.shoppingcart.exception;

import java.util.Arrays;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5223554311744914727L;
	
	private String errorCode;
	private Object[] parameters;

	public ServiceException(String errorCode, Object... parameters) {
		super(errorCode + ": " + Arrays.toString(parameters));
		
		this.errorCode = errorCode;
		this.parameters = parameters;
	}
}
