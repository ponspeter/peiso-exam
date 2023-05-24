package com.peiso.exam.service;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.KountaApiClient;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.OrdersResponse;
import com.peiso.exam.integration.response.ProductResponse;
import com.peiso.exam.integration.response.StaffResponse;
import com.peiso.exam.model.Order;
import com.peiso.exam.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KountaServiceImpl implements KountaService {

    private final KountaApiClient kountaApiClient;
    private final OrderService orderService;

    private final ProductService productService;

    public KountaServiceImpl(KountaApiClient kountaApiClient, OrderService orderService, ProductService productService) {
        this.kountaApiClient = kountaApiClient;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public ListResponse getAllProducts(String accessToken) {

        List<ProductResponse> products = kountaApiClient.getAllProducts(accessToken);

        if (!products.isEmpty()) {
            // add products in db
            addProduct(products);

            // Extract products from db
            List<Product> productsFromDb = productService.listProducts();

            return ListResponse
                    .builder()
                    .data(productsFromDb)
                    .totalCount(productsFromDb.size())
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
    public List<StaffResponse> getAllStaff(String accessToken) {
        return kountaApiClient.getAllStaff(accessToken);
    }

    @Override
    public ListResponse getAllOrders(String accessToken, String created_lte, String created_gte) {

        List<OrdersResponse> orders = kountaApiClient.getAllOrders(accessToken, created_lte, created_gte);

        if (!orders.isEmpty()) {
            // add orders in db
            addOrder(orders);

            // Extract orders from db
            List<Order> ordersFromDb = orderService.listOrders();

            return ListResponse.builder()
                    .data(ordersFromDb)
                    .totalCount(ordersFromDb.size())
                    .build();
        } else {
            return ListResponse.builder()
                    .data(null)
                    .totalCount(0)
                    .build();
        }
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {
        return kountaApiClient.authorize(authorizationRequest);
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
