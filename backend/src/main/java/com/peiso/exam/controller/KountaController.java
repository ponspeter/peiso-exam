package com.peiso.exam.controller;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.service.KountaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/kounta")
@Slf4j
public class KountaController {

    private final KountaService kountaService;

    public KountaController(KountaService kountaService) {
        this.kountaService = kountaService;
    }


    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody AuthorizationRequest authorizationRequest) {
        AuthorizationResponse response = kountaService.authorize(authorizationRequest);
        return response;
    }

    @PostMapping("/refresh-token")
    public AuthorizationResponse refreshToken(@RequestBody AuthorizationRequest authorizationRequest) {
        AuthorizationResponse response = kountaService.authorize(authorizationRequest);
        return response;
    }

    @GetMapping("/products/db")
    public ListResponse getAllProductsFromDb() {
        return kountaService.getAllProducts();
    }

    @GetMapping("/products/kounta")
    public ListResponse getAllProductsFromKounta(@RequestHeader("Authorization") String authorization) {
        return kountaService.getAllProducts(authorization);
    }

    @GetMapping("/orders/db")
    public ListResponse getAllOrdersFromDb(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate) {
        return kountaService.getAllOrders(startDate, endDate);
    }

    @GetMapping("/orders/kounta")
    public ListResponse getAllOrdersFromKounta(@RequestHeader("Authorization") String authorization,
                                     @RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate) {
        return kountaService.getAllOrders(authorization, startDate, endDate);
    }
}
