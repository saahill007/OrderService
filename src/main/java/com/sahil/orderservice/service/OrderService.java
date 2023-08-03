package com.sahil.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.orderservice.dto.InventoryResponse;
import com.sahil.orderservice.dto.OrderLineItemDto;
import com.sahil.orderservice.dto.OrderResponse;
import com.sahil.orderservice.model.Order;
import com.sahil.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepository repo;
	
	@Autowired
	WebClient webClient;

	public void placeOrderUsingOrderResponse(OrderResponse orderResponse) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		orderResponse.getOrderLineItems()
			.stream()
			.map(orderLineItem->mapToList(orderLineItem)).toList();
		
		List<String> skuCodes = orderResponse.getOrderLineItems()
			.stream()
			.map(orderlineItem->orderlineItem.getSkuCode())
			.toList();
		//Check whether the order is valid in inventory or not
		InventoryResponse[] inventoryResponses = webClient.get()
			.uri("http://localhost:8083/api/inventory/",uriBuilder->uriBuilder.queryParam("skuCode", skuCodes).build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		boolean isValid = true;
		for(int i=0;i<inventoryResponses.length;i++) {
			if(inventoryResponses[i].isProductInStock()==false) {
				isValid = false;
			}
		}
		if(isValid) {
			repo.save(order);
		}else {
			throw new IllegalArgumentException("The items from products is out of stock");
		}
//		repo.save(order);
		
		
		
		
	}

	private OrderLineItemDto mapToList(OrderLineItemDto orderLineItem) {
		OrderLineItemDto items = new OrderLineItemDto();
		items.setSkuCode(orderLineItem.getSkuCode());
		items.setPrice(orderLineItem.getPrice());
		items.setQuantity(orderLineItem.getQuantity());
		
		return items;
	}
	
	
}
