package com.acme.wheelmanager.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveUserResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String email;
}
