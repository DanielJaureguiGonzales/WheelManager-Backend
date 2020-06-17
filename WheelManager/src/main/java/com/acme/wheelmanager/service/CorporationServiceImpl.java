package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Corporation;
import com.acme.wheelmanager.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CorporationServiceImpl implements CorporationService{

    @Autowired
    private CorporationRepository corporationRepository;

    @Override
    public ResponseEntity<?> deleteCorporation(Long corporationId) {
        Corporation corporation = corporationRepository.findById(corporationId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", corporationId));
        corporationRepository.delete(corporation);
        return ResponseEntity.ok().build();
    }

    @Override
    public Corporation updateCorporation(Long corporationId, Corporation corporationRequest) {
        Corporation corporation = corporationRepository.findById(corporationId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", corporationId));
        corporation.setRuc(corporationRequest.getRuc());
        corporation.setName(corporationRequest.getName());
        corporation.setAddress(corporationRequest.getAddress());
        corporation.setPhone(corporationRequest.getPhone());
        return corporationRepository.save(corporation);
    }

    @Override
    public Corporation createCorporation(Corporation corporation) {
        return corporationRepository.save(corporation);
    }

    @Override
    public Corporation getCorporationById(Long corporationId) {
        return corporationRepository.findById(corporationId)
                .orElseThrow(() -> new ResourceNotFoundException("Corporation", "Id", corporationId));
    }

    @Override
    public Page<Corporation> getAllCorporations(Pageable pageable) {
        return corporationRepository.findAll(pageable);
    }
}

