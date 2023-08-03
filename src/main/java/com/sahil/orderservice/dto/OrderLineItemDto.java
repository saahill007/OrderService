package com.sahil.orderservice.dto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {

	private String skuCode;
	private BigDecimal price;
	private int quantity;
	
}
