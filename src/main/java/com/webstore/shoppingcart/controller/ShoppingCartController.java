package com.webstore.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webstore.shoppingcart.controller.base.ControllerBase;
import com.webstore.shoppingcart.dto.request.ShoppingCartRequestDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;
import com.webstore.shoppingcart.dto.response.ShoppingCartResponseDTO;
import com.webstore.shoppingcart.service.ShoppingCartService;

@RestController
@RequestMapping(path = "/api/v1/shoppingCarts")
public class ShoppingCartController extends ControllerBase {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class);
	
	@Autowired
	private ShoppingCartService service;
	
	@PostMapping(path = "/user/{id}/addItems")
	public ResponseEntity<ResponseDTO<ShoppingCartResponseDTO>> addItems(@Valid @RequestBody ShoppingCartRequestDTO request, @PathVariable(value = "id") String id) {
		LOG.debug("Initializing POST in /api/v1/shoppingCarts/user/{id}/addItems with request {} and id {}", request, id);
		
		ShoppingCartResponseDTO response = service.addItems(request, id);

		LOG.debug("Finishing POST in /api/v1/shoppingCarts/user/{id}/addItems with response {}", response);
		
		return ResponseEntity.
				status(HttpStatus.OK).
				body(new ResponseDTO<>(response));
	}
	
	@GetMapping(path = "/user/{id}/findPending")
	public ResponseEntity<ResponseDTO<ShoppingCartResponseDTO>> findPendingByUser(@PathVariable(value = "id") String id) {
		LOG.debug("Initializing GET in /api/v1/shoppingCarts/user/{id}/findPending with id {}", id);
		
		ShoppingCartResponseDTO response = service.findPendingByUser(id);

		LOG.debug("Finishing GET in /api/v1/shoppingCarts/user/{id}/findPending with response {}", response);

		return ResponseEntity.
				status(HttpStatus.OK).
				body(new ResponseDTO<>(response));
	}
	
	@PostMapping(path = "/user/{id}/finish")
	public ResponseEntity<ResponseDTO<ShoppingCartResponseDTO>> finishByUser(@PathVariable(value = "id") String id) {
		LOG.debug("Initializing POST in /api/v1/shoppingCarts/user/{id}/finish with and id {}", id);
		
		ShoppingCartResponseDTO response = service.finishByUser(id);
		
		LOG.debug("Finishing POST in /api/v1/shoppingCarts/user/{id}/finish with response {}", response);

		return ResponseEntity.
				status(HttpStatus.OK).
				body(new ResponseDTO<>(response));
	}
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<ResponseDTO<List<ShoppingCartResponseDTO>>> findAll() {
		LOG.debug("Initializing GET in /api/v1/shoppingCarts/findAll");
		
		List<ShoppingCartResponseDTO> response = service.findAll();

		LOG.debug("Finishing GET in /api/v1/shoppingCarts/findAll with response {}", response);
		
		return ResponseEntity.
				status(HttpStatus.OK).
				body(new ResponseDTO<>(response));
	}
	
	@GetMapping(path = "/findPending")
	public ResponseEntity<ResponseDTO<List<ShoppingCartResponseDTO>>> findPending() {
		LOG.debug("Initializing GET in /api/v1/shoppingCarts/findPending");
		
		List<ShoppingCartResponseDTO> response = service.findPending();

		LOG.debug("Finishing GET in /api/v1/shoppingCarts/findPending with response {}", response);
		
		return ResponseEntity.
				status(HttpStatus.OK).
				body(new ResponseDTO<>(response));
	}
}
