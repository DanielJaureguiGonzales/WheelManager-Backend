package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Corporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CorporationService {
    ResponseEntity<?> deleteCorporation(Long corporationId);
    Corporation updateCorporation(Long userId, Corporation corporationRequest);
    Corporation createCorporation(Corporation corporation);
    Corporation getCorporationById(Long corporationId);
    Page<Corporation> getAllCorporations(Pageable pageable);
}

