package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.Coordinate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {
    Page<Coordinate> findByDistrictId(Long districtId, Pageable pageable);
    Optional<Coordinate> findByIdAndDistrictId(Long id, Long districtId);
}