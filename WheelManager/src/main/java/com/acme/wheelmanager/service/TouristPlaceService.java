package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.TouristPlace;
import com.acme.wheelmanager.repository.TouristPlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TouristPlaceService {
    Page<TouristPlace> getAllTouristPlacesByDistrictId(Long districtId, Pageable pageable);
    TouristPlace getTouristPlaceByIdAndDistrictId(Long touristPlaceId, Long districtId);
    ResponseEntity<?> deleteTouristPlace(Long touristPlaceId);
    TouristPlace updateTouristPlace(Long touristPlaceId, TouristPlace touristPlaceRequest);
    TouristPlace createTouristPlace(TouristPlace touristPlaceRequest);
    TouristPlace getTouristPlaceById(Long touristPlaceId);
    Page<TouristPlace> getAllTouristPlaces(Pageable pageable);
}
