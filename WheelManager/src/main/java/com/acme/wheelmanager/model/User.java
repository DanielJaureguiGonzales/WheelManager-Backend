package com.acme.wheelmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
