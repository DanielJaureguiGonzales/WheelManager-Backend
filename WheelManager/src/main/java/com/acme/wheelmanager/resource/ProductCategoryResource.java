package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryResource extends AuditModel {
    private Long id;
    private String name;
    private String image_url;
}