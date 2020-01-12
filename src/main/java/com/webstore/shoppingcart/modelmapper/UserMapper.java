package com.webstore.shoppingcart.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webstore.shoppingcart.domain.entity.User;
import com.webstore.shoppingcart.dto.request.UserRequestDTO;
import com.webstore.shoppingcart.dto.response.UserResponseDTO;

@Component
public class UserMapper {

	@Autowired
	protected ModelMapper modelMapper;
	
	public User toDomainObject(UserRequestDTO request) {
		return modelMapper.map(request, User.class);
	}

	public void copyToDomainObject(UserRequestDTO request, User user) {
		modelMapper.map(request, user);
	}
	
	public UserResponseDTO toResponseObject(User user) {
		return modelMapper.map(user, UserResponseDTO.class);
	}

	public List<UserResponseDTO> toResponseObjectList(List<User> users) {
		return users.
				stream().
					map(user -> toResponseObject(user)).
				collect(Collectors.toList());
	}

}
