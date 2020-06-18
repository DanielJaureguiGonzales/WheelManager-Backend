package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.District;
import com.acme.wheelmanager.repository.DepartmentRepository;
import com.acme.wheelmanager.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Page<District> getAllDistrictsByDepartmentId(Long departmentId, Pageable pageable) {
        return districtRepository.findByDepartmentId(departmentId, pageable);
    }

    @Override
    public District createDistrict(Long departmentId, District district) {
        return departmentRepository.findById(departmentId).map(department -> {
            district.setDepartment(department);
            return districtRepository.save(district);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Post", "Id", departmentId));

    }

    @Override
    public District getDistrictByIdAndDepartmentId(Long departmentId, Long districtId) {
        return districtRepository.findByIdAndDepartmentId(districtId, departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + districtId +
                                " and PostId " + departmentId));
    }

    @Override
    public District updateDistrict(Long departmentId, Long districtId, District districtDetails) {
        if(!departmentRepository.existsById(departmentId))
            throw new ResourceNotFoundException("Post", "Id", departmentId);

        return districtRepository.findById(districtId).map(district -> {
            district.setName(districtDetails.getName());
            return districtRepository.save(district);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", districtId));

    }

    @Override
    public ResponseEntity<?> deleteDistrict(Long departmentId, Long districtId) {
        return districtRepository.findByIdAndDepartmentId(districtId, departmentId).map(comment -> {
            districtRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id " + districtId + " and PostId " + departmentId));

    }
}
