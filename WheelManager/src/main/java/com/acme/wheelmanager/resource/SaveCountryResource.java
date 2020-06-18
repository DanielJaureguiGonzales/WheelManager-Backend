package com.acme.wheelmanager.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveCountryResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String name;
}
