package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AlertService {
    Page<Alert> getAllAlertsByCoordinateId(Long coordinateId, Pageable pageable);
    Alert getAlertByIdAndCoordinateId(Long alertId, Long coordinateId);
    ResponseEntity<?> deleteAlert(Long alertId);
    Alert updateAlert(Long alertId, Alert alertRequest);
    Alert createAlert(Alert alertRequest);
    Alert getAlertById(Long alertId);
    Page<Alert> getAllAlerts(Pageable pageable);
}