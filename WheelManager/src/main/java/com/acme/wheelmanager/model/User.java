package com.acme.wheelmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "corporation_id" , referencedColumnName= "id")
    private Corporation corporation;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_promos",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "promo_id")})
    @JsonIgnore
    List<Promo> promos;

}
