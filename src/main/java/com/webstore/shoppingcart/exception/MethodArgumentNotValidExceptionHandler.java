package com.webstore.shoppingcart.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.webstore.shoppingcart.dto.response.ErrorResponseDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;

@Component
public class MethodArgumentNotValidExceptionHandler implements ExceptionHandler<MethodArgumentNotValidException> {

	@Override
	public ResponseEntity<ResponseDTO<Object>> handleException(MethodArgumentNotValidException exception) {
		List<ErrorResponseDTO> errors = new ArrayList<>();

		BindingResult bindingResult = exception.getBindingResult();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String field = fieldError.getField();
			String message = String.format("%s: %s", field, fieldError.getDefaultMessage());

			errors.add(new ErrorResponseDTO(field, message));
		}

		return ResponseEntity.
				status(HttpStatus.BAD_REQUEST).
				body(ResponseDTO.withErrors(errors));
	}

}
