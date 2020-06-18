package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Alert;
import com.acme.wheelmanager.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public Page<Alert> getAllAlertsByCoordinateId(Long coordinateId, Pageable pageable) {
        return alertRepository.findByCoordinateId(coordinateId,pageable);
    }

    @Override
    public Alert getAlertByIdAndCoordinateId(Long alertId, Long coordinateId) {
        return alertRepository.findByIdAndCoordinateId(alertId, coordinateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alert not found with Id " + alertId +
                                " and CoordinateId " + coordinateId));
    }


    @Override
    public ResponseEntity<?> deleteAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", alertId));
        alertRepository.delete(alert);
        return ResponseEntity.ok().build();
    }

    @Override
    public Alert updateAlert(Long alertId, Alert alertRequest) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert", "Id", alertId));
        alert.setName(alertRequest.getName());
        alert.setDescription(alertRequest.getDescription());
        return alertRepository.save(alert);
    }

    @Override
    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public Alert getAlertById(Long alertId) {
        return alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert", "Id", alertId));
    }

    @Override
    public Page<Alert> getAllAlerts(Pageable pageable) {
        return alertRepository.findAll(pageable);
    }
}