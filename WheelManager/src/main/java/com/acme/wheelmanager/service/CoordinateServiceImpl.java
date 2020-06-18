package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Coordinate;
import com.acme.wheelmanager.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CoordinateServiceImpl implements CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Override
    public Page<Coordinate> getAllCoordinatesByDistrictId(Long districtId, Pageable pageable) {
        return coordinateRepository.findByDistrictId(districtId,pageable);
    }

    @Override
    public Coordinate getCoordinateByIdAndDistrictId(Long coordinateId, Long districtId) {
        return coordinateRepository.findByIdAndDistrictId(coordinateId, districtId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Coordinate not found with Id " + coordinateId +
                                " and DistrictId " + districtId));
    }


    @Override
    public ResponseEntity<?> deleteCoordinate(Long coordinateId) {
        Coordinate coordinate = coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", coordinateId));
        coordinateRepository.delete(coordinate);
        return ResponseEntity.ok().build();
    }

    @Override
    public Coordinate updateCoordinate(Long coordinateId, Coordinate coordinateRequest) {
        Coordinate coordinate = coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new ResourceNotFoundException("Coordinate", "Id", coordinateId));
        coordinate.setLatitude(coordinateRequest.getLatitude());
        coordinate.setLongitude(coordinateRequest.getLongitude());
        return coordinateRepository.save(coordinate);
    }

    @Override
    public Coordinate createCoordinate(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }

    @Override
    public Coordinate getCoordinateById(Long coordinateId) {
        return coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new ResourceNotFoundException("Coordinate", "Id", coordinateId));
    }

    @Override
    public Page<Coordinate> getAllCoordinates(Pageable pageable) {
        return coordinateRepository.findAll(pageable);
    }
}