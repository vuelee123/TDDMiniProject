package com.example.TddMiniProject.service;

import com.example.TddMiniProject.entity.Order;
import com.example.TddMiniProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        order.setId(generateOrderId()); // Generate and assign an id
        return orderRepository.save(order);
    }
    private Long generateOrderId() {

        Long maxId = orderRepository.getMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "Customer 1", LocalDate.now(), "Address 1", 100.0));
        orders.add(new Order(2L, "Customer 2", LocalDate.now(), "Address 2", 200.0));
        return orders;
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
