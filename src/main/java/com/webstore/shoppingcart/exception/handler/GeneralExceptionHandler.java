package com.webstore.shoppingcart.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.webstore.shoppingcart.dto.response.ErrorResponseDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;

@Component
public class GeneralExceptionHandler implements ExceptionHandler<Exception> {

	@Autowired
	private MessageSource messageSource;

    @Override
	public ResponseEntity<ResponseDTO<Object>> handleException(Exception exception) {
		String message = messageSource.getMessage("M-99", null, LocaleContextHolder.getLocale());
		ErrorResponseDTO error = new ErrorResponseDTO("M-99" + ": " + message);

		return ResponseEntity.
				status(HttpStatus.BAD_REQUEST).
				body(ResponseDTO.withError(error));
	}

}
