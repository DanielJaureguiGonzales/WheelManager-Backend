package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.TouristPlace;
import com.acme.wheelmanager.repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TouristPlaceServiceImpl implements TouristPlaceService {

    @Autowired
    private TouristPlaceRepository touristPlaceRepository;

    @Override
    public Page<TouristPlace> getAllTouristPlacesByDistrictId(Long districtId, Pageable pageable) {
        return touristPlaceRepository.findByDistrictId(districtId,pageable);
    }

    @Override
    public TouristPlace getTouristPlaceByIdAndDistrictId(Long touristPlaceId, Long districtId) {
        return touristPlaceRepository.findByIdAndDistrictId(touristPlaceId, districtId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TouristPlace not found with Id " + touristPlaceId +
                                " and DistrictId " + districtId));
    }


    @Override
    public ResponseEntity<?> deleteTouristPlace(Long touristPlaceId) {
        TouristPlace touristPlace = touristPlaceRepository.findById(touristPlaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", touristPlaceId));
        touristPlaceRepository.delete(touristPlace);
        return ResponseEntity.ok().build();
    }

    @Override
    public TouristPlace updateTouristPlace(Long touristPlaceId, TouristPlace touristPlaceRequest) {
        TouristPlace touristPlace = touristPlaceRepository.findById(touristPlaceId)
                .orElseThrow(() -> new ResourceNotFoundException("TouristPlace", "Id", touristPlaceId));
        touristPlace.setName(touristPlaceRequest.getName());
        touristPlace.setAddress(touristPlaceRequest.getAddress());
        return touristPlaceRepository.save(touristPlace);
    }

    @Override
    public TouristPlace createTouristPlace(TouristPlace touristPlace) {
        return touristPlaceRepository.save(touristPlace);
    }

    @Override
    public TouristPlace getTouristPlaceById(Long touristPlaceId) {
        return touristPlaceRepository.findById(touristPlaceId)
                .orElseThrow(() -> new ResourceNotFoundException("TouristPlace", "Id", touristPlaceId));
    }

    @Override
    public Page<TouristPlace> getAllTouristPlaces(Pageable pageable) {
        return touristPlaceRepository.findAll(pageable);
    }
}