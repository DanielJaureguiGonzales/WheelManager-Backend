package com.acme.wheelmanager.service;


import com.acme.wheelmanager.model.Publicitie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PublicitieService {
    Page<Publicitie> getAllPublicitiesByCorporationId(Long corporationId, Pageable pageable);
    Publicitie getPublicitiesByIdAndCorporationId(Long corporationId, Long publicitieId);
    Publicitie createPublicitie(Long corporationId, Publicitie publicitie);
    Publicitie updatePublicitie(Long corporationId, Long publicitieId, Publicitie publicitieDetails);
    ResponseEntity<?> deletePublicitie(Long corporationId, Long publicitieId);
}





