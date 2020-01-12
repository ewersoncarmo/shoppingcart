package com.webstore.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webstore.shoppingcart.controller.base.ControllerBase;
import com.webstore.shoppingcart.dto.request.UserRequestDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;
import com.webstore.shoppingcart.dto.response.UserResponseDTO;
import com.webstore.shoppingcart.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController extends ControllerBase {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseDTO<UserResponseDTO>> create(@Valid @RequestBody UserRequestDTO request) {
		LOG.debug("Initializing POST in /api/v1/users with request {}", request);
			
		UserResponseDTO response = service.create(request);

		LOG.debug("Finishing POST in /api/v1/users with response {}", response);

		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(new ResponseDTO<>(response));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ResponseDTO<UserResponseDTO>> update(@Valid @RequestBody UserRequestDTO request, @PathVariable(value = "id") String id) {
		LOG.debug("Initializing PUT in /api/v1/users/{id} with request {} and id {}", request, id);
		
		UserResponseDTO response = service.update(request, id);

		LOG.debug("Finishing PUT in /api/v1/users/{id} with response {}", response);

		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(new ResponseDTO<>(response));
	}
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> find(@PathVariable(value = "id") String id) {
		LOG.debug("Initializing GET in /api/v1/users/{id} with id {}", id);
		
		UserResponseDTO response = service.find(id);

		LOG.debug("Finishing GET in /api/v1/users/{id} with response {}", response);

		return ResponseEntity.
                status(HttpStatus.OK).
                body(new ResponseDTO<>(response));
    }
	
	@GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> findAll() {
		LOG.debug("Initializing GET in /api/v1/users");
		
        List<UserResponseDTO> response = service.findAll();

        LOG.debug("Finishing GET in /api/v1/users with response {}", response);

        return ResponseEntity.
                status(HttpStatus.OK).
                body(new ResponseDTO<>(response));
    }
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable(value = "id") String id) {
		LOG.debug("Initializing DELETE in /api/v1/users/{id} with id {}", id);
		
        service.delete(id);

        LOG.debug("Finishing DELETE in /api/v1/users/{id}");

        return ResponseEntity.
                noContent().
                build();
    }
	
}
