package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductCategoryService {

    ResponseEntity<?> deleteProductCategory(Long productCategoryId);
    ProductCategory updateProductCategory(Long productCategoryId, ProductCategory productCategoryRequest);
    ProductCategory createProductCategory(ProductCategory productCategoryRequest);
    ProductCategory getProductCategoryById(Long productCategoryId);
    Page<ProductCategory> getAllProductCategories(Pageable pageable);
}
