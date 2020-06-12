package com.acme.wheelmanager.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "corporations")
@Getter
@Setter
public class Corporation extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    //
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , referencedColumnName= "id")
    private User user;
}
