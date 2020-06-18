package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Long> {
    Page<ProductCategory> findByProductId(Long productId, Pageable pageable);
    Optional<ProductCategory> findByIdAndProductId(Long id, Long productId);
}