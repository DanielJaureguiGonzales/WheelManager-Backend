package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Recommendation;
import com.acme.wheelmanager.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public Page<Recommendation> getAllRecommendationsByUserId(Long userId, Pageable pageable) {
        return recommendationRepository.findByUserId(userId,pageable);
    }

    @Override
    public Recommendation getRecommendationByIdAndUserId(Long recommendationId, Long userId) {
        return recommendationRepository.findByIdAndUserId(recommendationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recommendation not found with Id " + recommendationId +
                                " and UserId " + userId));
    }


    @Override
    public ResponseEntity<?> deleteRecommendation(Long recommendationId) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", recommendationId));
        recommendationRepository.delete(recommendation);
        return ResponseEntity.ok().build();
    }

    @Override
    public Recommendation updateRecommendation(Long recommendationId, Recommendation recommendationRequest) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation", "Id", recommendationId));
        recommendation.setDescription(recommendationRequest.getDescription());
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation createRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getRecommendationById(Long recommendationId) {
        return recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation", "Id", recommendationId));
    }

    @Override
    public Page<Recommendation> getAllRecommendations(Pageable pageable) {
        return recommendationRepository.findAll(pageable);
    }
}