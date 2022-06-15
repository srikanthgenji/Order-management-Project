package com.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.order.entity.Address;
import com.order.entity.OrderLine;
import com.order.entity.OrderLineStatus;
import com.order.entity.OrderProducts;
import com.order.entity.OrderStatus;
import com.order.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepository repo;
	
	private Address address;
	private OrderProducts orderProducts;
	private OrderLine orderLine;
	
	

	@Test
	 void testSaveOrder() {
		
		address = new Address();
		orderProducts = new OrderProducts();
		orderLine = new OrderLine();
		
		address.setAddressId(1);
		address.setCity("atp");
		address.setCountry("india");
		address.setPin(111111);
		
		orderLine.setOrderlineId(2);
		orderLine.setItem("laptop");
		orderLine.setPrice(60000);
		orderLine.setQuantity(1);
		orderLine.setEta(2);
		orderLine.setOrderLineStatus(OrderLineStatus.open);
		
		
		List<OrderLine> orderlinesList = new ArrayList<>();
		orderlinesList.add(orderLine);
		
		orderProducts.setOrderId(3);
		orderProducts.setTotalAmount(60000);
		orderProducts.setOrderDate("01-01-2022");
		orderProducts.setOrderStatus(OrderStatus.open);
		orderProducts.setAddress(address);
		orderProducts.setOrderlines(orderlinesList);
	
		Mockito.when(repo.save(orderProducts)).thenReturn(orderProducts);
		assertThat(orderService.saveOrder(orderProducts)).isEqualTo(orderProducts);
	}
	
	@Test
	 void testGetOrdersByPincodeWithMatchingValue() {
		
		address = new Address();
		orderProducts = new OrderProducts();
		orderLine = new OrderLine();
		
		address.setAddressId(1);
		address.setCity("atp");
		address.setCountry("india");
		address.setPin(111111);
		
		orderLine.setOrderlineId(2);
		orderLine.setItem("laptop");
		orderLine.setPrice(60000);
		orderLine.setQuantity(1);
		orderLine.setEta(2);
		orderLine.setOrderLineStatus(OrderLineStatus.open);
		
		
		List<OrderLine> orderlinesList = new ArrayList<>();
		orderlinesList.add(orderLine);
		
		orderProducts.setOrderId(3);
		orderProducts.setTotalAmount(60000);
		orderProducts.setOrderDate("01-01-2022");
		orderProducts.setOrderStatus(OrderStatus.open);
		orderProducts.setAddress(address);
		orderProducts.setOrderlines(orderlinesList);
		
		List<OrderProducts> ordersList = new ArrayList<>();
		ordersList.add(orderProducts);
		
		Integer pincode = 111111;
		Mockito.when(repo.findAll()).thenReturn(ordersList);
		List<OrderProducts> resultingOrdersList = orderService.getOrdersByPincode(pincode);
		assertEquals(pincode,resultingOrdersList.get(0).getAddress().getPin() );
		
	}
	
	@Test
	 void testGetOrdersByPincodeWithoutMatchingValue() {
		
		address = new Address();
		orderProducts = new OrderProducts();
		orderLine = new OrderLine();
		
		address.setAddressId(1);
		address.setCity("atp");
		address.setCountry("india");
		address.setPin(111111);
		
		orderLine.setOrderlineId(2);
		orderLine.setItem("laptop");
		orderLine.setPrice(60000);
		orderLine.setQuantity(1);
		orderLine.setEta(2);
		orderLine.setOrderLineStatus(OrderLineStatus.open);
		
		
		List<OrderLine> orderlinesList = new ArrayList<>();
		orderlinesList.add(orderLine);
		
		orderProducts.setOrderId(3);
		orderProducts.setTotalAmount(60000);
		orderProducts.setOrderDate("01-01-2022");
		orderProducts.setOrderStatus(OrderStatus.open);
		orderProducts.setAddress(address);
		orderProducts.setOrderlines(orderlinesList);
		
		List<OrderProducts> ordersList = new ArrayList<>();
		ordersList.add(orderProducts);
		
		Integer pincode = 111112;
		Mockito.when(repo.findAll()).thenReturn(ordersList);
		List<OrderProducts> resultingOrdersList = orderService.getOrdersByPincode(pincode);
		assertEquals(new ArrayList<>(),resultingOrdersList);
		
	}
	
	@Test
	 void testGetOrderByIdWithMatchingValue() {
		
		address = new Address();
		orderProducts = new OrderProducts();
		orderLine = new OrderLine();
		
		address.setAddressId(1);
		address.setCity("atp");
		address.setCountry("india");
		address.setPin(111111);
		
		orderLine.setOrderlineId(2);
		orderLine.setItem("laptop");
		orderLine.setPrice(60000);
		orderLine.setQuantity(1);
		orderLine.setEta(2);
		orderLine.setOrderLineStatus(OrderLineStatus.open);
		
		
		List<OrderLine> orderlinesList = new ArrayList<>();
		orderlinesList.add(orderLine);
		
		orderProducts.setOrderId(3);
		orderProducts.setTotalAmount(60000);
		orderProducts.setOrderDate("01-01-2022");
		orderProducts.setOrderStatus(OrderStatus.open);
		orderProducts.setAddress(address);
		orderProducts.setOrderlines(orderlinesList);
	
		Integer id = 3;
		Mockito.when(repo.findById(id)).thenReturn(Optional.ofNullable(orderProducts));
		assertThat(orderService.getOrderById(id)).isEqualTo(orderProducts);
	}

	@Test
	 void testGetOrderByIdWithoutMatchingValue() {
		
		Integer id = 2;
		Mockito.when(repo.findById(id)).thenReturn(Optional.ofNullable(orderProducts));
		OrderProducts orderProducts = orderService.getOrderById(id);
		assertEquals(null, orderProducts);
	}


}
