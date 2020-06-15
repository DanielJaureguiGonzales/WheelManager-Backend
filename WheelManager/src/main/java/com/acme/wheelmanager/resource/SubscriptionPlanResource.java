package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPlanResource extends AuditModel {
    private Long id;
    private String subscription_plan_name;
    private Double price;
    private String description;
}