package com.peiso.exam.controller;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.integration.request.AuthorizationRequest;
import com.peiso.exam.integration.response.AuthorizationResponse;
import com.peiso.exam.integration.response.StaffResponse;
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

    @GetMapping("/products")
    public ListResponse getAllProducts(@RequestHeader("Authorization") String authorization) {
        return kountaService.getAllProducts(authorization);
    }

    @GetMapping("/staff")
    public List<StaffResponse> getAllStaff(@RequestHeader("Authorization") String authorization) {
        List<StaffResponse> response = kountaService.getAllStaff(authorization);
        return response;
    }

    @GetMapping("/orders")
    public ListResponse getAllOrders(@RequestHeader("Authorization") String authorization,
                                             @RequestParam("created_lte") String created_lte,
                                             @RequestParam("created_gte") String created_gte) {
        return kountaService.getAllOrders(authorization, created_lte, created_gte);
    }
}
