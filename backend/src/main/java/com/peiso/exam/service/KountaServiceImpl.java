package com.peiso.exam.service;

import com.peiso.exam.integration.KountaApiClient;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import com.peiso.exam.integration.response.StaffResponse;
import com.peiso.exam.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KountaServiceImpl implements KountaService {

    private final KountaApiClient kountaApiClient;
    private final OrderService orderService;

    public KountaServiceImpl(KountaApiClient kountaApiClient, OrderService orderService) {
        this.kountaApiClient = kountaApiClient;
        this.orderService = orderService;
    }

    @Override
    public List<ProductResponse> getAllProducts(String accessToken) {
        return kountaApiClient.getAllProducts(accessToken);
    }

    @Override
    public List<StaffResponse> getAllStaff(String accessToken) {
        return kountaApiClient.getAllStaff(accessToken);
    }

    @Override
    public List<OrdersResponse> getAllOrders(String accessToken) {
        return kountaApiClient.getAllOrders(accessToken);
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {
        return kountaApiClient.authorize(authorizationRequest);
    }

    @Override
    public boolean addOrder(List<OrdersResponse> orders) {

        orders.stream().map(order -> {
            return Order
                    .builder()
                    .order_id(order.getId())
                    .status(order.getStatus())
                    .notes(order.getNotes())
                    .siteId(order.getSiteId())
                    .registerId(order.getRegisterId())
                    .staffId(order.getStaffId())
                    .total(order.getTotal())
                    .totalTax(order.getTotalTax())
                    .paid(order.getPaid())
                    .tips(order.getTips())
                    .deleted(order.isDeleted())
                    .createdAt(order.getCreatedAt())
                    .updatedAt(order.getUpdatedAt())
                    .build();
        }).collect(Collectors.toList());

        Order order = new Order();
        List <Order> orderList = new ArrayList<>();

        return orderService.addOrder(orderList);
    }
}
