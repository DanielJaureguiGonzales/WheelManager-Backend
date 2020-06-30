package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.ProductCategory;
import com.acme.wheelmanager.resource.ProductCategoryResource;
import com.acme.wheelmanager.resource.SaveProductCategoryResource;
import com.acme.wheelmanager.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "product_categories", description = "ProductCategories API")
@RestController
@RequestMapping("/api")

public class ProductCategoryController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Operation(summary = "Get ProductCategories", description = "Get All ProductCategories by Pages", tags = { "product_categories" })
    @GetMapping("/product_categories")
    public Page<ProductCategoryResource> getAllProductCategorys(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<ProductCategory> postsPage = productCategoryService.getAllProductCategories(pageable);
        List<ProductCategoryResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get ProductCategory by Id", description = "Get a ProductCategories by specifying Id", tags = { "product_categories" })
    @GetMapping("/product_categories/{id}")
    public ProductCategoryResource getProductCategoryById(
            @Parameter(description="ProductCategory Id")
            @PathVariable(name = "id") Long productCategoryId) {
        return convertToResource(productCategoryService.getProductCategoryById(productCategoryId));
    }

    @GetMapping("/products/{productId}/product_categories")
    public Page<ProductCategoryResource> getAllProductCategoriesByProductId(
            @PathVariable(name = "productId") Long productId,
            Pageable pageable) {
        Page<ProductCategory> promoPage = productCategoryService.getAllProductCategoriesByProductId(productId, pageable);
        List<ProductCategoryResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/products/{productId}/product_categories/{productCategoryId}")
    public ProductCategoryResource getProductCategoryByIdAndProductId(@PathVariable(name = "productId") Long productId,
                                                                 @PathVariable(name = "productCategoryId") Long productCategoryId) {
        return convertToResource(productCategoryService.getProductCategoryByIdAndProductId(productId, productCategoryId));
    }

    @Operation(summary = "Create ProductCategory ", description = "Create an ProductCategory ", tags = { "product_categories" })
    @PostMapping("/product_categories")
    public ProductCategoryResource createProductCategory(@Valid @RequestBody SaveProductCategoryResource resource)  {
        ProductCategory productCategory = convertToEntity(resource);
        return convertToResource(productCategoryService.createProductCategory(productCategory));
    }
    @Operation(summary = "Update ProductCategory by Id", description = "Update an ProductCategory by specifying Id", tags = { "product_categories" })
    @PutMapping("/product_categories/{id}")
    public ProductCategoryResource updateProductCategory(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveProductCategoryResource resource) {
        ProductCategory productCategory = convertToEntity(resource);
        return convertToResource(productCategoryService.updateProductCategory(postId, productCategory));
    }

    @Operation(summary = "Delete ProductCategory by Id", description = "Delete an ProductCategory by specifying Id", tags = { "product_categories" })
    @DeleteMapping("/product_categories/{id}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable(name = "id") Long productCategoryId) {
        return productCategoryService.deleteProductCategory(productCategoryId);
    }
    private ProductCategory convertToEntity(SaveProductCategoryResource resource) {
        return mapper.map(resource, ProductCategory.class);
    }

    private ProductCategoryResource convertToResource(ProductCategory entity) {
        return mapper.map(entity, ProductCategoryResource.class);
    }
}