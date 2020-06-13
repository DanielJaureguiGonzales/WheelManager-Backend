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
import java.util.List;

@Entity
@Table(name = "promos")
@Getter
@Setter
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corporation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Corporation corporation;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "promos")
    @JsonIgnore
    private List<User> users;
}
