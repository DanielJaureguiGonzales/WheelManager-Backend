package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertResource extends AuditModel {
    private Long id;
    private String name;
    private String description;
}