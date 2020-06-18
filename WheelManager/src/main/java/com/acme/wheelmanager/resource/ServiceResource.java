package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResource extends AuditModel {
    private Long id;
    private int rating;
    private String name;
    private String description;
    private String category;
    private Double price;
}