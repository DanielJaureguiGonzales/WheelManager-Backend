package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    Page<Department> getAllDepartmentsByCountryId(Long countryId, Pageable pageable);
    Department createDepartment(Long departmentId, Department department);
    Department getDepartmentByIdAndCountryId(Long countryId, Long departmentId);
    Department updateDepartment(Long countryId, Long departmentId, Department departmentDetails);
    ResponseEntity<?> deleteDepartment(Long countryId, Long departmentId);
}
