package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.ProductCategory;
import com.acme.wheelmanager.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ResponseEntity<?> deleteProductCategory(Long productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", productCategoryId));
        productCategoryRepository.delete(productCategory);
        return ResponseEntity.ok().build();
    }

    @Override
    public ProductCategory updateProductCategory(Long productCategoryId, ProductCategory productCategoryRequest) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductCategory", "Id", productCategoryId));
        productCategory.setImage_url(productCategoryRequest.getImage_url());
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory getProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductCategory", "Id", productCategoryId));
    }

    @Override
    public Page<ProductCategory> getAllProductCategories(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }
}
