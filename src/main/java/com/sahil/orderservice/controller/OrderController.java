package com.sahil.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.orderservice.dto.OrderResponse;
import com.sahil.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String placeOrder(@RequestBody OrderResponse orderResponse) {
		
		service.placeOrderUsingOrderResponse(orderResponse);
		return "Order placed Successfully";
	}

}
