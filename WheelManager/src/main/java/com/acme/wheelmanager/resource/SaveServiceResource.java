package com.acme.wheelmanager.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveServiceResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private int rating;

    @NotNull
    @NotBlank
    @Size(max = 25)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private Double price;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private Double category;
}