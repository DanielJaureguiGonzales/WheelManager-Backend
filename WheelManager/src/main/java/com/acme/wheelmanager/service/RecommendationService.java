package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RecommendationService {
    Page<Recommendation> getAllRecommendationsByUserId(Long userId, Pageable pageable);
    Recommendation getRecommendationByIdAndUserId(Long recommendationId, Long userId);
    ResponseEntity<?> deleteRecommendation(Long recommendationId);
    Recommendation updateRecommendation(Long recommendationId, Recommendation recommendationRequest);
    Recommendation createRecommendation(Recommendation recommendationRequest);
    Recommendation getRecommendationById(Long recommendationId);
    Page<Recommendation> getAllRecommendations(Pageable pageable);
}
