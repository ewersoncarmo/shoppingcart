package com.webstore.shoppingcart.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.webstore.shoppingcart.dto.response.ErrorResponseDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;
import com.webstore.shoppingcart.exception.ServiceException;

@Component
public class ServiceExceptionHandler implements ExceptionHandler<ServiceException> {

	@Autowired
	private MessageSource messageSource;

    @Override
	public ResponseEntity<ResponseDTO<Object>> handleException(ServiceException exception) {
		String message = messageSource.getMessage(exception.getErrorCode(), exception.getParameters(), LocaleContextHolder.getLocale());
		ErrorResponseDTO error = new ErrorResponseDTO(exception.getErrorCode() + ": " + message);

		return ResponseEntity.
				status(HttpStatus.BAD_REQUEST).
				body(ResponseDTO.withError(error));
	}

}
