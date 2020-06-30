package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.SubscriptionPlan;
import com.acme.wheelmanager.resource.SaveSubscriptionPlanResource;
import com.acme.wheelmanager.resource.SubscriptionPlanResource;
import com.acme.wheelmanager.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "subscription_plans", description = "SubscriptionPlans API")
@RestController
@RequestMapping("/api")

public class SubscriptionPlanController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @Operation(summary = "Get SubscriptionPlans", description = "Get All SubscriptionPlans by Pages", tags = { "subscription_plans" })
    @GetMapping("/subscription_plans")
    public Page<SubscriptionPlanResource> getAllSubscriptionPlans(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<SubscriptionPlan> postsPage = subscriptionPlanService.getAllSubscriptionPlans(pageable);
        List<SubscriptionPlanResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get SubscriptionPlan by Id", description = "Get a SubscriptionPlans by specifying Id", tags = { "subscription_plans" })
    @GetMapping("/subscription_plans/{id}")
    public SubscriptionPlanResource getSubscriptionPlanById(
            @Parameter(description="SubscriptionPlan Id")
            @PathVariable(name = "id") Long subscriptionPlanId) {
        return convertToResource(subscriptionPlanService.getSubscriptionPlanById(subscriptionPlanId));
    }

    @Operation(summary = "Create SubscriptionPlan ", description = "Create a SubscriptionPlan ", tags = { "subscription_plans" })
    @PostMapping("/subscription_plans")
    public SubscriptionPlanResource createSubscriptionPlan(@Valid @RequestBody SaveSubscriptionPlanResource resource)  {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.createSubscriptionPlan(subscriptionPlan));
    }
    @Operation(summary = "Update SubscriptionPlan by Id", description = "Update a SubscriptionPlan by specifying Id", tags = { "subscription_plans" })
    @PutMapping("/subscription_plans/{id}")
    public SubscriptionPlanResource updateSubscriptionPlan(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveSubscriptionPlanResource resource) {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.updateSubscriptionPlan(postId, subscriptionPlan));
    }

    @Operation(summary = "Delete SubscriptionPlan by Id", description = "Delete a SubscriptionPlan by specifying Id", tags = { "subscription_plans" })
    @DeleteMapping("/subscription_plans/{id}")
    public ResponseEntity<?> deleteSubscriptionPlan(@PathVariable(name = "id") Long subscriptionPlanId) {
        return subscriptionPlanService.deleteSubscriptionPlan(subscriptionPlanId);
    }
    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }
}
