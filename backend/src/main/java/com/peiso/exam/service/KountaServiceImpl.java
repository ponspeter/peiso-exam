package com.peiso.exam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.KountaApiClient;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import com.peiso.exam.model.Order;
import com.peiso.exam.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KountaServiceImpl implements KountaService {

    private final KountaApiClient kountaApiClient;
    private final OrderService orderService;

    private final ProductService productService;

    private final RedisCacheManager redisCacheManager;

    private final ObjectMapper objectMapper;

    private final String BEARER = "Bearer ";

    public KountaServiceImpl(KountaApiClient kountaApiClient, OrderService orderService,
                             ProductService productService, RedisCacheManager redisCacheManager, ObjectMapper objectMapper) {
        this.kountaApiClient = kountaApiClient;
        this.orderService = orderService;
        this.productService = productService;
        this.redisCacheManager = redisCacheManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public ListResponse getAllProductsFromKounta() throws JsonProcessingException {

        String redisValue = (String) redisCacheManager.fetchFromCache("token");
        AuthorizationResponse authorizationResponse = objectMapper.readValue(redisValue, AuthorizationResponse.class);


        List<ProductResponse> products = kountaApiClient.getAllProducts(BEARER.concat(authorizationResponse.getAccessToken()));

        if (!products.isEmpty()) {
            // add products in db
            addProduct(products);

            return ListResponse
                    .builder()
                    .data(products)
                    .totalCount(products.size())
                    .build();
        } else {
            return ListResponse
                    .builder()
                    .data(null)
                    .totalCount(0)
                    .build();
        }
    }

    @Override
    public ListResponse getAllProducts() {
        // Extract products from db
        List<Product> productsFromDb = productService.listProducts();

        return ListResponse
                .builder()
                .data(productsFromDb)
                .totalCount(productsFromDb.size())
                .build();
    }

    @Override
    public ListResponse getAllOrdersFromKounta(String startDate, String endDate) throws JsonProcessingException {

        String created_lte = startDate + "T00:00:00Z";
        String created_gte = endDate + "T23:59:59Z";

        String redisValue = (String) redisCacheManager.fetchFromCache("token");
        AuthorizationResponse authorizationResponse = objectMapper.readValue(redisValue, AuthorizationResponse.class);

        List<OrdersResponse> orders = kountaApiClient.getAllOrders(BEARER.concat(authorizationResponse.getAccessToken()), created_lte, created_gte);

        if (!orders.isEmpty()) {
            // add orders in db
            addOrder(orders);

            return ListResponse.builder()
                    .data(orders)
                    .totalCount(orders.size())
                    .build();
        } else {
            return ListResponse.builder()
                    .data(null)
                    .totalCount(0)
                    .build();
        }
    }

    @Override
    public ListResponse getAllOrders() {
        // Extract orders from db
        // assume for now that the orders are already in the db
        // this can be change to use the param as filter
        List<Order> ordersFromDb = orderService.listOrders();

        return ListResponse.builder()
                .data(ordersFromDb)
                .totalCount(ordersFromDb.size())
                .build();
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) throws JsonProcessingException {
        AuthorizationResponse authorizationResponse = kountaApiClient.authorize(authorizationRequest);
        redisCacheManager.addToCache("token", objectMapper.writeValueAsString(authorizationResponse), 1, TimeUnit.HOURS);
        return authorizationResponse;
    }

    @Override
    public boolean addOrder(List<OrdersResponse> orders) {

        List <Order> orderList = orders.stream().map(order -> {
            return Order
                    .builder()
                    .orderId(order.getId())
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

        return orderService.addOrder(orderList);
    }

    @Override
    public boolean addProduct(List<ProductResponse> products) {

        List<Product> productList = products.stream().map(product -> {
            return Product
                    .builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .alwaysPrompt(product.isAlwaysPrompt())
                    .code(product.getCode())
                    .friendlyName(product.getFriendlyName())
                    .isModifier(product.isModifier())
                    .number(product.getNumber())
                    .build();
        }).collect(Collectors.toList());

        return productService.addProduct(productList);
    }
}
