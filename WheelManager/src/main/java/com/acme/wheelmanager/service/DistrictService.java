package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DistrictService {
    Page<District> getAllDistrictsByDepartmentId(Long departmentId, Pageable pageable);
    District createDistrict(Long departmentId, District district);
    District getDistrictByIdAndDepartmentId(Long departmentId, Long districtId);
    District updateDistrict(Long departmentId, Long districtId, District districtDetails);
    ResponseEntity<?> deleteDistrict(Long departmentId, Long districtId);
}