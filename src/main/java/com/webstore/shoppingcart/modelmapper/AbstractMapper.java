package com.webstore.shoppingcart.modelmapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<Request, Model, Response> {

	@Autowired
	protected ModelMapper modelMapper;
	
	public abstract Model toModel(Request request);
	
	public abstract Response toResponse(Model model);
	
	public abstract List<Response> toResponseList(List<Model> modelList);
	
}
