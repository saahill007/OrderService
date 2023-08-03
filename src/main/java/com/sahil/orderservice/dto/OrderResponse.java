package com.sahil.orderservice.dto;

import java.util.List;


import com.sahil.orderservice.model.OrderLineItem;


import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
	
	private List<OrderLineItemDto> orderLineItems;

}
