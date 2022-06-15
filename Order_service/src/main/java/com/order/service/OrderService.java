package com.order.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.OrderProducts;
import com.order.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public OrderProducts saveOrder(OrderProducts orderProducts) {
		return repo.save(orderProducts);
	}

	public OrderProducts getOrderById(Integer orderId) {
		Optional<OrderProducts> order1 = repo.findById(orderId);
		OrderProducts order2 = null;
		if(order1.isPresent()) {
			order2 = order1.get();
		}
		return order2;
	}

	public List<OrderProducts> getOrdersByPincode(int pincode) {
		
		List<OrderProducts> orderList = repo.findAll();
		List<OrderProducts> orderProducts = new ArrayList<>();
		for(OrderProducts or:orderList) {
			if(or.getAddress().getPin()==pincode) {
				orderProducts.add(or);
			}
		}
		
		return orderProducts;
	}
	
	
	
}
