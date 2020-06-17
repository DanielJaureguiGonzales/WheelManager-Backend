package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Service;
import com.acme.wheelmanager.resource.SaveServiceResource;
import com.acme.wheelmanager.resource.ServiceResource;
import com.acme.wheelmanager.service.ServiceService;
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
public class ServiceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/corporations/{corporationId}/services")
    public Page<ServiceResource> getAllServicesByCorporationId(
            @PathVariable(name = "corporationId") Long corporationId,
            Pageable pageable) {
        Page<Service> servicePage = serviceService.getAllServicesByCorporationId(corporationId, pageable);
        List<ServiceResource> resources = servicePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/corporations/{corporationId}/services/{serviceId}")
    public ServiceResource getServiceByIdAndCorporationById(@PathVariable(name = "corporationId") Long corporationId,
                                                            @PathVariable(name = "serviceId") Long serviceId) {
        return convertToResource(serviceService.getServiceByIdAndCorporationId(corporationId, serviceId));
    }

    @PostMapping("/corporations/{corporationId}/services")
    public ServiceResource createComment(@PathVariable(name = "corporationId") Long corporationId,
                                         @Valid @RequestBody SaveServiceResource resource) {
        return convertToResource(serviceService.createService(corporationId, convertToEntity(resource)));

    }

    @PutMapping("/corporations/{corporationId}/services/{serviceId}")
    public ServiceResource updateService(@PathVariable(name = "corporationId") Long corporationId,
                                         @PathVariable(name = "serviceId") Long serviceId,
                                         @Valid @RequestBody SaveServiceResource resource) {
        return convertToResource(serviceService.updateService(corporationId, serviceId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{corporationId}/services/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable(name = "corporationId") Long corporationId,
                                           @PathVariable(name = "serviceId") Long serviceId) {
        return serviceService.deleteService(corporationId, serviceId);
    }

    private Service convertToEntity(SaveServiceResource resource) {
        return mapper.map(resource, Service.class);
    }
    private ServiceResource convertToResource(Service entity) {
        return mapper.map(entity, ServiceResource.class);
    }
}
