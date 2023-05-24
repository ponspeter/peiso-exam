package com.peiso.exam.service;

import com.peiso.exam.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listProducts();

    public boolean addProduct(List<Product> products);
}
