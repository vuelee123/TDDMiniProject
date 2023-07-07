package com.example.TddMiniProject;

import com.example.TddMiniProject.controller.OrderController;
import com.example.TddMiniProject.entity.Order;
import com.example.TddMiniProject.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
class TddMiniProjectApplicationTests {


	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, "Customer 1", LocalDate.now(), "Address 1", 100.0));
		orders.add(new Order(2L, "Customer 2", LocalDate.now(), "Address 2", 200.0));

		when(orderService.getAllOrders()).thenReturn(orders);

		ResponseEntity<List<Order>> response = orderController.getAllOrders();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(orders, response.getBody());
	}

	@Test
	public void testGetOrderById() {
		Order order = new Order(1L, "Customer 1", LocalDate.now(), "Address 1", 100.0);

		when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

		ResponseEntity<Order> response = orderController.getOrderById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(order, response.getBody());
	}

	@Test
	public void testGetOrderById_NotFound() {
		when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

		ResponseEntity<Order> response = orderController.getOrderById(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertFalse(response.hasBody());
	}

	@Test
	public void testCreateOrder() {
		Order order = new Order(null, "Customer 1", LocalDate.now(), "Address 1", 100.0);
		Order createdOrder = new Order(1L, "Customer 1", LocalDate.now(), "Address 1", 100.0);

		when(orderService.saveOrder(order)).thenReturn(createdOrder);

		ResponseEntity<Order> response = orderController.createOrder(order);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(createdOrder, response.getBody());
	}

	@Test
	public void testUpdateOrder() {
		Order order = new Order(1L, "Customer 1", LocalDate.now(), "Address 1", 100.0);
		Order updatedOrder = new Order(1L, "Updated Customer", LocalDate.now(), "Updated Address", 200.0);

		when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
		doNothing().when(orderService).updateOrder(updatedOrder);

		ResponseEntity<Order> response = orderController.updateOrder(1L, updatedOrder);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedOrder, response.getBody());
	}


	@Test
	public void testUpdateOrder_NotFound() {
		Order updatedOrder = new Order(1L, "Updated Customer", LocalDate.now(), "Updated Address", 200.0);

		when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

		ResponseEntity<Order> response = orderController.updateOrder(1L, updatedOrder);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertFalse(response.hasBody());
	}

	@Test
	public void testDeleteOrder() {
		when(orderService.getOrderById(1L)).thenReturn(Optional.of(new Order()));
		doNothing().when(orderService).deleteOrder(1L);

		ResponseEntity<Void> response = orderController.deleteOrder(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertFalse(response.hasBody());

		verify(orderService, times(1)).deleteOrder(1L);
	}


	@Test
	public void testDeleteOrder_NotFound() {
		when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

		ResponseEntity<Void> response = orderController.deleteOrder(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertFalse(response.hasBody());

		verify(orderService, times(0)).deleteOrder(1L);
	}
}
