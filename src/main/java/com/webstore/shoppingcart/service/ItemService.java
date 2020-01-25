package com.webstore.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.shoppingcart.domain.entity.Item;
import com.webstore.shoppingcart.dto.request.ItemRequestDTO;
import com.webstore.shoppingcart.dto.response.ItemResponseDTO;
import com.webstore.shoppingcart.exception.ServiceException;
import com.webstore.shoppingcart.modelmapper.ItemMapper;
import com.webstore.shoppingcart.persistence.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	public ItemResponseDTO create(ItemRequestDTO request) {
		validate(request);
		
		Item item = itemMapper.toDomainObject(request);

		return save(item);
	}

	public ItemResponseDTO update(ItemRequestDTO request, String id) {
		Item item = findById(id);

		if (request.getName() != item.getName()) {
			validate(request);
		}
		
		itemMapper.copyToDomainObject(request, item);
		
		return save(item);
	}

	public ItemResponseDTO find(String id) {
		Item item = findById(id);
		
		return itemMapper.toResponseObject(item);
	}
	
	public List<ItemResponseDTO> findAll() {
		List<Item> items = repository.findAll();
		
		return itemMapper.toResponseObjectList(items);
	}

	public Item findById(String id) {
		// M-4=No Item with ID ({0}) was found.
		Item item = repository.findById(id).
			orElseThrow(() -> new ServiceException("M-4", id));
		
		return item;
	}
	
	public void delete(String id) {
		Item item = findById(id);
		
		// M-7=Item with ID ({0}) belongs to a Shopping Carts and can not be deleted.
		shoppingCartService.findShoppingCartItemByItem(item.getId())
			.ifPresent(s -> {
		 		throw new ServiceException("M-7", item.getId());
		 	});
		
		repository.delete(item);
	}
	
	private ItemResponseDTO save(Item item) {
		item = repository.save(item);
		
		return itemMapper.toResponseObject(item);
	}
	
	private void validate(ItemRequestDTO request) {
		// M-3=Item with given Name ({0}) already exists.
		repository.findByName(request.getName()).
			ifPresent(r -> {
				throw new ServiceException("M-3", r.getName()); 
				});
	}

}
