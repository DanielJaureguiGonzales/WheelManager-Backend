package com.acme.wheelmanager.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class PromoResource {
    private Long id;
    private String name;
    private String description;
    private String image_url;
}
