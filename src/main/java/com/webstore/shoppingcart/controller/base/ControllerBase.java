package com.webstore.shoppingcart.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.webstore.shoppingcart.dto.response.ResponseDTO;
import com.webstore.shoppingcart.exception.ServiceException;
import com.webstore.shoppingcart.exception.handler.GeneralExceptionHandler;
import com.webstore.shoppingcart.exception.handler.MethodArgumentNotValidExceptionHandler;
import com.webstore.shoppingcart.exception.handler.ServiceExceptionHandler;

public abstract class ControllerBase {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerBase.class);
	
	@Autowired
	private MethodArgumentNotValidExceptionHandler methodArgumentNotValidExceptionHandler;
	
	@Autowired
	private ServiceExceptionHandler serviceExceptionHandler;
	
	@Autowired
	private GeneralExceptionHandler generalExceptionHandler;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(MethodArgumentNotValidException exception) {
		LOG.error("Error while validating the request.", exception);
		
		return methodArgumentNotValidExceptionHandler.handleException(exception);
    }
	
	@ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(ServiceException exception) {
		LOG.error("Business error while processing the request.", exception);
		
		return serviceExceptionHandler.handleException(exception);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(Exception exception) {
		LOG.error("Unexpected error while processing the request.", exception);
		
		return generalExceptionHandler.handleException(exception);
    }

}
