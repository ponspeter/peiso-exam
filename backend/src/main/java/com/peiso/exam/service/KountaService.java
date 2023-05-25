package com.peiso.exam.service;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;

import java.util.List;

public interface KountaService {

    ListResponse getAllProducts(String accessToken);

    ListResponse getAllProducts();

    ListResponse getAllOrders(String accessToken, String startDate, String endDate);

    ListResponse getAllOrders(String startDate, String endDate);

    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);

    boolean addOrder(List<OrdersResponse> orders);

    boolean addProduct(List<ProductResponse> products);
}
