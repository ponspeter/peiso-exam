package com.peiso.exam.service;

import com.peiso.exam.model.Order;
import com.peiso.exam.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public boolean addOrder(List<Order> orders) {
        try {
            orderRepository.saveAll(orders);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
