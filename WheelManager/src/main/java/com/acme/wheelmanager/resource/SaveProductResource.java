package com.acme.wheelmanager.resource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveProductResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private int rating;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private int units_int_stock;

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
