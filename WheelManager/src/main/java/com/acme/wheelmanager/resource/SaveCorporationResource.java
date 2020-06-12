package com.acme.wheelmanager.resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveCorporationResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private int ruc;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String phone;
}
