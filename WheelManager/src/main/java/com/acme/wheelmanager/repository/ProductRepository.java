package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCorporationId(Long corporationId, Pageable pageable);
    Optional<Product> findByIdAndCorporationId(Long id, Long corporationId);
}
