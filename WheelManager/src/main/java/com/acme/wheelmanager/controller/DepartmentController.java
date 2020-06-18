package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Department;
import com.acme.wheelmanager.model.District;
import com.acme.wheelmanager.resource.DepartmentResource;
import com.acme.wheelmanager.resource.DistrictResource;
import com.acme.wheelmanager.resource.SaveDepartmentResource;
import com.acme.wheelmanager.resource.SaveDistrictResource;
import com.acme.wheelmanager.service.DepartmentService;
import com.acme.wheelmanager.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/countries/{countryId}/departments")
    public Page<DepartmentResource> getAllDepartmentsByCountryId(
            @PathVariable(name = "countryId") Long countryId,
            Pageable pageable) {
        Page<Department> departmentPage = departmentService.getAllDepartmentsByCountryId(countryId, pageable);
        List<DepartmentResource> resources = departmentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/countries/{countryId}/departments/{departmentId}")
    public DepartmentResource getDepartmentByIdAndCountryId(@PathVariable(name = "countryId") Long countryId,
                                                   @PathVariable(name = "departmentId") Long departmentId) {
        return convertToResource(departmentService.getDepartmentByIdAndCountryId(countryId, departmentId));
    }

    @PostMapping("/countries/{countryId}/departments")
    public DepartmentResource createDepartment(@PathVariable(name = "countryId") Long countryId,
                                         @Valid @RequestBody SaveDepartmentResource resource) {
        return convertToResource(departmentService.createDepartment(countryId, convertToEntity(resource)));

    }

    @PutMapping("/countries/{countryId}/departments/{departmentId}")
    public DepartmentResource updateDepartment(@PathVariable(name = "countryId") Long countryId,
                                         @PathVariable(name = "departmentId") Long departmentId,
                                         @Valid @RequestBody SaveDepartmentResource resource) {
        return convertToResource(departmentService.updateDepartment(countryId, departmentId, convertToEntity(resource)));
    }

    @DeleteMapping("/countries/{countryId}/departments/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "countryId") Long countryId,
                                           @PathVariable(name = "departmentId") Long departmentId) {
        return departmentService.deleteDepartment(countryId, departmentId);
    }

    private Department convertToEntity(SaveDepartmentResource resource) {
        return mapper.map(resource, Department.class);
    }

    private DepartmentResource convertToResource(Department entity) {
        return mapper.map(entity, DepartmentResource.class);
    }


}
