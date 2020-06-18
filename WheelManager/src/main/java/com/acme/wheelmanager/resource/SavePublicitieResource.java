package com.acme.wheelmanager.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePublicitieResource {
    @NotNull
    @NotBlank
    @Size(max = 25)
    @Column(unique = true)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private Double image_URL;
}
