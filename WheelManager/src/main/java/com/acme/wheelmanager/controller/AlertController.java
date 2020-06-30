package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Alert;
import com.acme.wheelmanager.resource.AlertResource;
import com.acme.wheelmanager.resource.SaveAlertResource;
import com.acme.wheelmanager.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "alerts", description = "Alerts API")
@RestController
@RequestMapping("/api")

public class AlertController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AlertService alertService;

    @Operation(summary = "Get Alerts", description = "Get All Alerts by Pages", tags = { "alerts" })
    @GetMapping("/alerts")
    public Page<AlertResource> getAllAlerts(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Alert> postsPage = alertService.getAllAlerts(pageable);
        List<AlertResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Alert by Id", description = "Get a Alerts by specifying Id", tags = { "alerts" })
    @GetMapping("/alerts/{id}")
    public AlertResource getAlertById(
            @Parameter(description="Alerts Id")
            @PathVariable(name = "id") Long alertId) {
        return convertToResource(alertService.getAlertById(alertId));
    }

    @GetMapping("/coordinates/{coordinateId}/alerts")
    public Page<AlertResource> getAllAlertsByCoordinateId(
            @PathVariable(name = "coordinateId") Long coordinateId,
            Pageable pageable) {
        Page<Alert> promoPage = alertService.getAllAlertsByCoordinateId(coordinateId, pageable);
        List<AlertResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/coordinates/{coordinateId}/alerts/{alertId}")
    public AlertResource getAlertByIdAndCoordinateId(@PathVariable(name = "coordinateId") Long coordinateId,
                                                                 @PathVariable(name = "alertId") Long alertId) {
        return convertToResource(alertService.getAlertByIdAndCoordinateId(coordinateId, alertId));
    }


    @Operation(summary = "Create Alerts ", description = "Create a Alert ", tags = { "alerts" })
    @PostMapping("/alerts")
    public AlertResource createAlert(@RequestBody @Valid SaveAlertResource resource)  {
        Alert alert = convertToEntity(resource);
        return convertToResource(alertService.createAlert(alert));
    }

    @Operation(summary = "Update Alert by Id", description = "Update a Alert by specifying Id", tags = { "alerts" })
    @PutMapping("/alerts/{id}")
    public AlertResource updateAlert(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveAlertResource resource) {
        Alert alert = convertToEntity(resource);
        return convertToResource(alertService.updateAlert(postId, alert));
    }

    @Operation(summary = "Delete Alert by Id", description = "Delete a Alert by specifying Id", tags = { "alerts" })
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable(name = "id") Long alertId) {
        return alertService.deleteAlert(alertId);
    }
    private Alert convertToEntity(@Valid SaveAlertResource resource) {
        return mapper.map(resource, Alert.class);
    }

    private AlertResource convertToResource(Alert entity) {
        return mapper.map(entity, AlertResource.class);
    }
}