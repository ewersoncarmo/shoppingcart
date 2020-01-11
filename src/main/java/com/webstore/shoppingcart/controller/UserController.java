package com.webstore.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webstore.shoppingcart.controller.base.ControllerBase;
import com.webstore.shoppingcart.domain.User;
import com.webstore.shoppingcart.dto.request.UserRequestDTO;
import com.webstore.shoppingcart.dto.response.ResponseDTO;
import com.webstore.shoppingcart.dto.response.UserResponseDTO;
import com.webstore.shoppingcart.modelmapper.UserMapper;
import com.webstore.shoppingcart.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController extends ControllerBase {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping
	public ResponseEntity<ResponseDTO<UserResponseDTO>> save(@Valid @RequestBody UserRequestDTO request) {
		User user = service.save(userMapper.toModel(request));

		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(new ResponseDTO<>(userMapper.toResponse(user)));
	}
	
	@GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> findAll() {
        List<User> users = service.findAll();

        return ResponseEntity.
                status(HttpStatus.OK).
                body(new ResponseDTO<>(userMapper.toResponseList(users)));
    }
}
