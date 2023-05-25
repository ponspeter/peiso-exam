package com.peiso.exam.integration;

import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "kounta-api", url = "https://api.kounta.com/v1")
public interface KountaApiClient {

    @PostMapping(value = "/token.json")
    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);

    @GetMapping("/companies/me/products.json")
    List<ProductResponse> getAllProducts(@RequestHeader("Authorization") String authorization);

    @GetMapping ("/companies/me/orders.json?created_lte={created_lte}&created_gte={created_gte}")
    List<OrdersResponse> getAllOrders(@RequestHeader String authorization, @PathVariable("created_lte") String created_lte, @PathVariable("created_gte") String created_gte);
}
