package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.District;
import com.acme.wheelmanager.resource.DistrictResource;
import com.acme.wheelmanager.resource.SaveDistrictResource;
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
public class DistrictController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DistrictService districtService;

    @GetMapping("/departments/{departmentId}/districts")
    public Page<DistrictResource> getAllDistrictsByDepartmentId(
            @PathVariable(name = "departmentId") Long departmentId,
            Pageable pageable) {
        Page<District> districtPage = districtService.getAllDistrictsByDepartmentId(departmentId, pageable);
        List<DistrictResource> resources = districtPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/departments/{departmentId}/districts/{districtId}")
    public DistrictResource getDistrictByIdAndDepartmentId(@PathVariable(name = "departmentId") Long departmentId,
                                                   @PathVariable(name = "districtId") Long districtId) {
        return convertToResource(districtService.getDistrictByIdAndDepartmentId(departmentId, districtId));
    }

    @PostMapping("/departments/{departmentId}/districts")
    public DistrictResource createDistrict(@PathVariable(name = "departmentId") Long departmentId,
                                         @Valid @RequestBody SaveDistrictResource resource) {
        return convertToResource(districtService.createDistrict(departmentId, convertToEntity(resource)));

    }

    @PutMapping("/departments/{departmentId}/districts/{districtId}")
    public DistrictResource updateDistrict(@PathVariable(name = "departmentId") Long departmentId,
                                         @PathVariable(name = "districtId") Long districtId,
                                         @Valid @RequestBody SaveDistrictResource resource) {
        return convertToResource(districtService.updateDistrict(departmentId, districtId, convertToEntity(resource)));
    }

    @DeleteMapping("/departments/{departmentId}/districts/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable(name = "departmentId") Long departmentId,
                                           @PathVariable(name = "districtId") Long districtId) {
        return districtService.deleteDistrict(departmentId, districtId);
    }

    private District convertToEntity(SaveDistrictResource resource) {
        return mapper.map(resource, District.class);
    }

    private DistrictResource convertToResource(District entity) {
        return mapper.map(entity, DistrictResource.class);
    }


}
