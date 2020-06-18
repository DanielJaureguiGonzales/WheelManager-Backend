package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Department;
import com.acme.wheelmanager.repository.CountryRepository;
import com.acme.wheelmanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Page<Department> getAllDepartmentsByCountryId(Long countryId, Pageable pageable) {
        return departmentRepository.findByCountryId(countryId, pageable);
    }

    @Override
    public Department createDepartment(Long countryId, Department department) {
        return countryRepository.findById(countryId).map(country -> {
            department.setCountry(country);
            return departmentRepository.save(department);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Post", "Id", countryId));

    }

    @Override
    public Department getDepartmentByIdAndCountryId(Long countryId, Long departmentId) {
        return departmentRepository.findByIdAndCountryId(departmentId, countryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + departmentId +
                                " and PostId " + countryId));
    }

    @Override
    public Department updateDepartment(Long countryId, Long departmentId, Department departmentDetails) {
        if(!countryRepository.existsById(countryId))
            throw new ResourceNotFoundException("Post", "Id", countryId);

        return departmentRepository.findById(departmentId).map(department -> {
            department.setName(departmentDetails.getName());
            return departmentRepository.save(department);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", departmentId));

    }

    @Override
    public ResponseEntity<?> deleteDepartment(Long countryId, Long departmentId) {
        return departmentRepository.findByIdAndCountryId(departmentId, countryId).map(department -> {
            departmentRepository.delete(department);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id " + departmentId + " and PostId " + countryId));

    }
}
