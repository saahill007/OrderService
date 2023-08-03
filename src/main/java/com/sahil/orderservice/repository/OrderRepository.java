package com.sahil.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahil.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
