package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ServiceService {
    Page<Service> getAllServicesByCorporationId(Long corporationId, Pageable pageable);
    Service getServiceByIdAndCorporationId(Long corporationId, Long serviceId);
    Service createService(Long corporationId, Service service);
    Service updateService(Long corporationId, Long serviceId, Service serviceDetails);
    ResponseEntity<?> deleteService(Long corporationId, Long serviceId);
}
