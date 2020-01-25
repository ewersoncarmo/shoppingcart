package com.webstore.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.shoppingcart.domain.entity.User;
import com.webstore.shoppingcart.dto.request.UserRequestDTO;
import com.webstore.shoppingcart.dto.response.UserResponseDTO;
import com.webstore.shoppingcart.exception.ServiceException;
import com.webstore.shoppingcart.modelmapper.UserMapper;
import com.webstore.shoppingcart.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	public UserResponseDTO create(UserRequestDTO request) {
		validate(request);
		
		User user = userMapper.toDomainObject(request);

		return save(user);
	}

	public UserResponseDTO update(UserRequestDTO request, String id) {
		User user = findById(id);

		if (request.getName() != user.getName()) {
			validate(request);
		}
		
		userMapper.copyToDomainObject(request, user);
		
		return save(user);
	}

	public UserResponseDTO find(String id) {
		User user = findById(id);
		
		return userMapper.toResponseObject(user);
	}
	
	public List<UserResponseDTO> findAll() {
		List<User> users = repository.findAll();
		
		return userMapper.toResponseObjectList(users);
	}

	public User findById(String id) {
		// M-2=No User with ID ({0}) was found.
		User user = repository.findById(id).
			orElseThrow(() -> new ServiceException("M-2", id));
		
		return user;
	}
	
	public void delete(String id) {
		User user = findById(id);
		
		// M-6=User with ID ({0}) belongs to a Shopping Carts and can not be deleted.
		shoppingCartService.findShoppingCartByUser(user.getId())
			.ifPresent(s -> {
		 		throw new ServiceException("M-6", user.getId());
		 	});
		
		repository.delete(user);
	}
	
	private UserResponseDTO save(User user) {
		user = repository.save(user);
		
		return userMapper.toResponseObject(user);
	}
	
	private void validate(UserRequestDTO request) {
		// M-1=User with given Name ({0}) already exists.
		repository.findByName(request.getName()).
			ifPresent(r -> {
				throw new ServiceException("M-1", r.getName()); 
				});
	}

}
