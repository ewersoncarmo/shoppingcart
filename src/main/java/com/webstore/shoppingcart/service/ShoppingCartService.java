package com.webstore.shoppingcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.shoppingcart.domain.entity.Item;
import com.webstore.shoppingcart.domain.entity.ShoppingCart;
import com.webstore.shoppingcart.domain.entity.ShoppingCartItem;
import com.webstore.shoppingcart.domain.entity.User;
import com.webstore.shoppingcart.domain.enumerator.PurchaseStatus;
import com.webstore.shoppingcart.dto.request.ShoppingCartItemRequestDTO;
import com.webstore.shoppingcart.dto.response.ShoppingCartItemResponseDTO;
import com.webstore.shoppingcart.dto.response.ShoppingCartResponseDTO;
import com.webstore.shoppingcart.exception.ServiceException;
import com.webstore.shoppingcart.modelmapper.ItemMapper;
import com.webstore.shoppingcart.persistence.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private ItemMapper itemMapper;
	
	public ShoppingCartResponseDTO addItems(ShoppingCartItemRequestDTO request, String userId) {
		User user = userService.findById(userId);
		
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser_IdAndPurchaseStatus(user.getId(), PurchaseStatus.PENDING)
			.orElse(new ShoppingCart(user));
		
		Item item = itemService.findById(request.getItemId());

		ShoppingCartItem shoppingCartItem = shoppingCart.getItems()
				.stream()
					.filter(a -> a.getItem().getId().equals(item.getId()))
					.findAny()
					.orElse(null);
		
		if (shoppingCartItem != null) {
			LOG.debug("Item already exists in the Shopping Cart. It will be updated.");
			
			shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + request.getQuantity());
			shoppingCartItem.setTotalAmount(shoppingCartItem.getTotalAmount()
					.add(item.getAmount()
							.multiply(BigDecimal.valueOf(request.getQuantity()))));
		} else {
			LOG.debug("Item does not exist in the Shopping Cart. It will be added.");

			shoppingCartItem = ShoppingCartItem
					.builder()
						.item(item)
						.quantity(request.getQuantity())
						.totalAmount(item.getAmount()
								.multiply(BigDecimal.valueOf(request.getQuantity())))
					.build();
			
			shoppingCart.getItems().add(shoppingCartItem);
		}
		
		shoppingCart.setTotalAmount(BigDecimal.valueOf(shoppingCart.getItems()
				.stream()
				.mapToDouble(i -> i.getTotalAmount().doubleValue())
				.sum()));
		
		shoppingCartRepository.save(shoppingCart);

		return handleResponse(shoppingCart);
	}
	
	public ShoppingCartResponseDTO findPendingByUser(String userId) {
		ShoppingCart shoppingCart = findPendingShoppingCartByUser(userId);
		
		return handleResponse(shoppingCart);
	}
	
	public ShoppingCartResponseDTO finishByUser(String userId) {
		ShoppingCart shoppingCart = findPendingShoppingCartByUser(userId);
		
		shoppingCart.setPurchaseStatus(PurchaseStatus.FINISHED);
		shoppingCartRepository.save(shoppingCart);
		
		return handleResponse(shoppingCart);
	}
	
	public List<ShoppingCartResponseDTO> findAll() {
		return shoppingCartRepository.findAll()
				.stream()
					.map(s -> handleResponse(s))
					.sorted((s1, s2) -> s1.getTotalAmount().compareTo(s2.getTotalAmount()))
				.collect(Collectors.toList());
	}

	public List<ShoppingCartResponseDTO> findPending() {
		return shoppingCartRepository.findAll()
				.stream()
					.filter(s -> PurchaseStatus.PENDING.equals(s.getPurchaseStatus()))
					.map(s -> handleResponse(s))
					.sorted((s1, s2) -> s1.getTotalAmount().compareTo(s2.getTotalAmount()))
				.collect(Collectors.toList());
	}
	
	public Optional<ShoppingCart> findShoppingCartByUser(String userId) {
		return shoppingCartRepository.findByUser_Id(userId);
	}

	public Optional<ShoppingCart> findShoppingCartItemByItem(String itemId) {
		return shoppingCartRepository.findByItem_Id(itemId);
	}
	
	private ShoppingCart findPendingShoppingCartByUser(String userId) {
		User user = userService.findById(userId);
		
		// M-5=No pending Shopping Cart for User with ID ({0}) was found.
		ShoppingCart shoppingCart = shoppingCartRepository.findByUser_IdAndPurchaseStatus(user.getId(), PurchaseStatus.PENDING)
			.orElseThrow(() -> new ServiceException("M-5", user.getId()));
		
		return shoppingCart;
	}
	
	private ShoppingCartResponseDTO handleResponse(ShoppingCart shoppingCart) {
		ShoppingCartResponseDTO response = new ShoppingCartResponseDTO();
		
		response.setId(shoppingCart.getId());
		response.setUser(shoppingCart.getUser());
		response.setPurchaseStatus(shoppingCart.getPurchaseStatus().getDescription());
	
		List<ShoppingCartItemResponseDTO> shoppingCartItemsResponseDTO = new ArrayList<>();
		shoppingCart.getItems().forEach(i -> {
			ShoppingCartItemResponseDTO shoppingCartItemResponseDTO = ShoppingCartItemResponseDTO
					.builder()
						.item(itemMapper.toResponseObject(i.getItem()))
						.quantity(i.getQuantity())
						.totalAmount(i.getTotalAmount())
					.build();
			
			shoppingCartItemsResponseDTO.add(shoppingCartItemResponseDTO);
		});
	
		response.setItems(shoppingCartItemsResponseDTO
				.stream()
					.sorted((i1, i2) -> i1.getItem().getName().compareTo(i2.getItem().getName()))
					.sorted((i1, i2) -> i1.getTotalAmount().compareTo(i2.getTotalAmount()))
				.collect(Collectors.toList()));
		response.setTotalAmount(shoppingCart.getTotalAmount());
		
		return response;
	}
}
