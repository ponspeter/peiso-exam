package com.peiso.exam.service;

import com.peiso.exam.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> listOrders();
    boolean addOrder(List<Order> orders);
}
