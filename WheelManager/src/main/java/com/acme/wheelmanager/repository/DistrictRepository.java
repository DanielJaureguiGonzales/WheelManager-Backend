package com.acme.wheelmanager.repository;

import com.acme.wheelmanager.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    Page<District> findByDepartmentId(Long departmentId, Pageable pageable);
    Optional<District> findByIdAndDepartmentId(Long districtId, Long departmentId);
}
