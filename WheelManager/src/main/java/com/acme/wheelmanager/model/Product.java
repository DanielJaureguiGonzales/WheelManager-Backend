package com.acme.wheelmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Size(max = 30)
    @Column(unique = true)
    private String category;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private  Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corporation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Corporation corporation;
}
