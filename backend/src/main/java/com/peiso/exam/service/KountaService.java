package com.peiso.exam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;

import java.util.List;

public interface KountaService {

    ListResponse getAllProductsFromKounta() throws JsonProcessingException;

    ListResponse getAllProducts();

    ListResponse getAllOrdersFromKounta(String startDate, String endDate) throws JsonProcessingException;

    ListResponse getAllOrders();

    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws JsonProcessingException;

    boolean addOrder(List<OrdersResponse> orders);

    boolean addProduct(List<ProductResponse> products);
}
