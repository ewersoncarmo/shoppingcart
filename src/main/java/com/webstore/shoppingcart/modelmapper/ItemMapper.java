package com.webstore.shoppingcart.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webstore.shoppingcart.domain.entity.Item;
import com.webstore.shoppingcart.dto.request.ItemRequestDTO;
import com.webstore.shoppingcart.dto.response.ItemResponseDTO;

@Component
public class ItemMapper {

	@Autowired
	protected ModelMapper modelMapper;
	
	public Item toDomainObject(ItemRequestDTO request) {
		return modelMapper.map(request, Item.class);
	}

	public void copyToDomainObject(ItemRequestDTO request, Item item) {
		modelMapper.map(request, item);
	}
	
	public ItemResponseDTO toResponseObject(Item item) {
		return modelMapper.map(item, ItemResponseDTO.class);
	}

	public List<ItemResponseDTO> toResponseObjectList(List<Item> items) {
		return items.
				stream().
					map(item -> toResponseObject(item)).
				collect(Collectors.toList());
	}

}
