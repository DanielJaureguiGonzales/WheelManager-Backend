package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Advertising;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertisingRepository  extends JpaRepository<Advertising,Long> {
    Page<Advertising> findByUserId(Long userId, Pageable pageable);
    Optional<Advertising> findByIdAndUserId(Long id, Long userId);
}