package org.chernov.catalogservice.service;


import lombok.RequiredArgsConstructor;
import org.chernov.catalogservice.entity.Product;
import org.chernov.catalogservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(String productId) {
        if(productId == null || productId.isEmpty()){
            throw new IllegalArgumentException("Product id cannot be null or empty");
        }

        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }
}
