package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePromoResource extends AuditModel {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    @Size(max = 255)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String image_url;
}
