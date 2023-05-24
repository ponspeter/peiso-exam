package com.peiso.exam.service;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import com.peiso.exam.integration.response.StaffResponse;
import com.peiso.exam.model.Product;

import java.util.List;

public interface KountaService {

    ListResponse getAllProducts(String accessToken);

    List<StaffResponse> getAllStaff(String accessToken);

    ListResponse getAllOrders(String accessToken, String created_lte, String created_gte);

    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);

    boolean addOrder(List<OrdersResponse> orders);

    boolean addProduct(List<ProductResponse> products);
}
