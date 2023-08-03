package com.sahil.orderservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="t_order_line_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private int quantity;
	
}
