package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.SubscriptionPlan;
import com.acme.wheelmanager.model.User;
import com.acme.wheelmanager.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public class SubscriptionPlanServiceImpl implements SubscriptionPlanService{

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public ResponseEntity<?> deleteSubscriptionPlan(Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", subscriptionPlanId));
        subscriptionPlanRepository.delete(subscriptionPlan);
        return ResponseEntity.ok().build();
    }

    @Override
    public SubscriptionPlan updateSubscriptionPlan(Long subscriptionPlanId, SubscriptionPlan subscriptionPlanRequest) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("SubscriptionPlan", "Id", subscriptionPlanId));
        /*subscriptionPlan.setUsername(subscriptionPlanRequest.getUsername());
        subscriptionPlan.setPassword(subscriptionPlanRequest.getPassword());
        subscriptionPlan.setEmail(subscriptionPlanRequest.getEmail());*/
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public SubscriptionPlan getSubscriptionPlanById(Long subscriptionPlanId) {
        return subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("SubscriptionPlan", "Id", subscriptionPlanId));
    }

    @Override
    public Page<SubscriptionPlan> getAllSubscriptionPlans(Pageable pageable) {
        return subscriptionPlanRepository.findAll(pageable);
    }
}
