package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Page<Service> findByCorporationId(Long corporationId, Pageable pageable);
    Optional<Service> findByIdAndCorporationId(Long id, Long corporationId);
}