package com.webstore.shoppingcart.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.webstore.shoppingcart.domain.User;
import com.webstore.shoppingcart.dto.request.UserRequestDTO;
import com.webstore.shoppingcart.dto.response.UserResponseDTO;

@Component
public class UserMapper extends AbstractMapper<UserRequestDTO, User, UserResponseDTO> {

	@Override
	public User toModel(UserRequestDTO request) {
		return modelMapper.map(request, User.class);
	}

	@Override
	public UserResponseDTO toResponse(User model) {
		return modelMapper.map(model, UserResponseDTO.class);
	}

	@Override
	public List<UserResponseDTO> toResponseList(List<User> modelList) {
		return modelList.
				stream().
					map(user -> toResponse(user)).
				collect(Collectors.toList());
	}

}
