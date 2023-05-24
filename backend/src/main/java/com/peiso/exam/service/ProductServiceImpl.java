package com.peiso.exam.service;

import com.peiso.exam.model.Product;
import com.peiso.exam.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean addProduct(List<Product> products) {
        if(products != null) {
            productRepository.saveAll(products);
            return true;
        }
        return false;
    }
}
