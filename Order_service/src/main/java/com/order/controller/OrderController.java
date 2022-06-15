package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.OrderProducts;
import com.order.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/saveOrder")
	public OrderProducts saveOrder(@RequestBody OrderProducts orderProducts) {
		OrderProducts order = null;
		if(orderProducts!=null) {
			order = orderService.saveOrder(orderProducts);
		}
		return order;
	}

	@GetMapping("/getOrderById/{orderId}")
	public OrderProducts getOrderById( @PathVariable Integer orderId) {
		OrderProducts order = null;
		if(orderId!=null) {
			order = orderService.getOrderById(orderId);
		}
		return order;
	}
	
	@GetMapping("/getOrdersByPincode/{pincode}")
	public List<OrderProducts> getOrdersByPincode(@PathVariable Integer pincode) {
		List<OrderProducts> ordersList= null;
		if(pincode!=null) {
			ordersList = orderService.getOrdersByPincode(pincode);
		}
		return ordersList;
	}
}
