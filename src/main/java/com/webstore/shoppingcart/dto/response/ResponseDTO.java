package com.webstore.shoppingcart.dto.response;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ResponseDTO<T> {

	private T data;
	private List<ErrorResponseDTO> errors;

	public ResponseDTO(T data){
		this.data = data;
	}

	public static ResponseDTO<Object> withErrors(List<ErrorResponseDTO> errors) {
		ResponseDTO<Object> response = new ResponseDTO<>();
		response.setErrors(errors);

		return response;
	}

	public static ResponseDTO<Object> withError(ErrorResponseDTO error) {
		return withErrors(Arrays.asList(error));
	}

}
