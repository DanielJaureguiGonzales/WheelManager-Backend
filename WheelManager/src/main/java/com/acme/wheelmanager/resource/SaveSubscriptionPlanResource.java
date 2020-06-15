package com.acme.wheelmanager.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveSubscriptionPlanResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    //@Column(unique = true)
    private String subscription_plan_name;

    @NotNull
    @NotBlank
    private Double price;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String description;
}
