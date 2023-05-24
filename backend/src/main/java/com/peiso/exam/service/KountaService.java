package com.peiso.exam.service;

import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import com.peiso.exam.integration.response.StaffResponse;

import java.util.List;

public interface KountaService {

    List<ProductResponse> getAllProducts(String accessToken);

    List<StaffResponse> getAllStaff(String accessToken);

    List<OrdersResponse> getAllOrders(String accessToken, String created_lte, String created_gte);

    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);

    boolean addOrder(List<OrdersResponse> orders);
}
