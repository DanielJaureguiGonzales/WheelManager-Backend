package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllProductsByCorporationId(Long corporationId, Pageable pageable);
    Product getProductByIdAndCorporationId(Long corporationId, Long productId);
    Product createProduct(Long corporationId, Product product);
    Product updateProduct(Long corporationId, Long productId, Product productDetails);
    ResponseEntity<?> deleteProduct(Long corporationId, Long productId);
}

