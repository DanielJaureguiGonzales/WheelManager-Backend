package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisingResource extends AuditModel {
    private Long id;
    private String image_url;
}