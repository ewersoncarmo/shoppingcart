package com.webstore.shoppingcart.exception;

import org.springframework.http.ResponseEntity;

import com.webstore.shoppingcart.dto.response.ResponseDTO;

public interface ExceptionHandler<T extends Exception> {

	ResponseEntity<ResponseDTO<Object>> handleException(T exception);
}
