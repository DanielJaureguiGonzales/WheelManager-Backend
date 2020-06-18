package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinateResource extends AuditModel {
    private Long id;
    private Double latitude;
    private Double longitude;
}