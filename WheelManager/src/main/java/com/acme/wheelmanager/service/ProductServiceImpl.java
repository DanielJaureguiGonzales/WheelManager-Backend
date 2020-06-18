package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Product;
import com.acme.wheelmanager.repository.CorporationRepository;
import com.acme.wheelmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private CorporationRepository corporationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProductsByCorporationId(Long corporationId, Pageable pageable) {
        return productRepository.findByCorporationId(corporationId, pageable);
    }

    @Override
    public Product getProductByIdAndCorporationId(Long corporationId, Long productId) {
        return productRepository.findByIdAndCorporationId(productId, corporationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with Id " + productId +
                                " and CorporationId " + corporationId));
    }

    @Override
    public Product createProduct(Long corporationId, Product product) {
        return corporationRepository.findById(corporationId).map(corporation -> {
            product.setCorporation(corporation);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Corporation", "Id", corporationId));
    }

    @Override
    public Product updateProduct(Long corporationId, Long productId, Product productDetails) {
        if(!corporationRepository.existsById(corporationId))
            throw new ResourceNotFoundException("Corporation", "Id", corporationId);

        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            product.setRating(productDetails.getRating());
            product.setUnits_int_stock(productDetails.getUnits_int_stock());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long corporationId, Long productId) {
        return productRepository.findByIdAndCorporationId(productId, corporationId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Product not found with Id " + productId + " and CorporationId " + corporationId));
    }
}


