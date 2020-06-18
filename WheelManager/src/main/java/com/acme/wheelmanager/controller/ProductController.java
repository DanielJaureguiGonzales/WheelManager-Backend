package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Product;
import com.acme.wheelmanager.resource.ProductResource;
import com.acme.wheelmanager.resource.SaveProductResource;
import com.acme.wheelmanager.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService productService;

    @GetMapping("/corporations/{corporationId}/products")
    public Page<ProductResource> getAllProductsByCorporationId(
            @PathVariable(name = "corporationId") Long corporationId,
            Pageable pageable) {
        Page<Product> productPage = productService.getAllProductsByCorporationId(corporationId, pageable);
        List<ProductResource> resources = productPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/corporations/{corporationId}/products/{productId}")
    public ProductResource getProductByIdAndCorporationById(@PathVariable(name = "corporationId") Long corporationId,
                                                   @PathVariable(name = "productId") Long productId) {
        return convertToResource(productService.getProductByIdAndCorporationId(corporationId, productId));
    }

    @PostMapping("/corporations/{corporationId}/products")
    public ProductResource createProduct(@PathVariable(name = "corporationId") Long corporationId,
                                         @Valid @RequestBody SaveProductResource resource) {
        return convertToResource(productService.createProduct(corporationId, convertToEntity(resource)));

    }

    @PutMapping("/corporations/{corporationId}/products/{productId}")
    public ProductResource updateProduct(@PathVariable(name = "corporationId") Long corporationId,
                                         @PathVariable(name = "productId") Long productId,
                                         @Valid @RequestBody SaveProductResource resource) {
        return convertToResource(productService.updateProduct(corporationId, productId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{corporationId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "corporationId") Long corporationId,
                                           @PathVariable(name = "productId") Long productId) {
        return productService.deleteProduct(corporationId, productId);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return mapper.map(resource, Product.class);
    }
    private ProductResource convertToResource(Product entity) {
        return mapper.map(entity, ProductResource.class);
    }
}
