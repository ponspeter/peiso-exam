package com.peiso.exam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.service.KountaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthorizationResponse authorize(@RequestBody AuthorizationRequest authorizationRequest) throws JsonProcessingException {
        AuthorizationResponse response = kountaService.authorize(authorizationRequest);
        return response;
    }

    @PostMapping("/refresh-token")
    public AuthorizationResponse refreshToken(@RequestBody AuthorizationRequest authorizationRequest) throws JsonProcessingException {
        AuthorizationResponse response = kountaService.authorize(authorizationRequest);
        return response;
    }

    @GetMapping("/products/db")
    public ListResponse getAllProductsFromDb() {
        return kountaService.getAllProducts();
    }

    @GetMapping("/products")
    public ListResponse getAllProductsFromKounta() throws JsonProcessingException {
         return kountaService.getAllProductsFromKounta();
    }

    @GetMapping("/orders/db")
    public ListResponse getAllOrdersFromDb(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate) {
        return kountaService.getAllOrders(startDate, endDate);
    }

    @GetMapping("/orders")
    public ListResponse getAllOrdersFromKounta(@RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate) throws JsonProcessingException {
        return kountaService.getAllOrdersFromKounta(startDate, endDate);
    }
}
