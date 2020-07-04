package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Recommendation;
import com.acme.wheelmanager.resource.RecommendationResource;
import com.acme.wheelmanager.resource.SaveRecommendationResource;
import com.acme.wheelmanager.service.RecommendationService;
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

@Tag(name = "recommendations", description = "Recommendations API")
@RestController
@RequestMapping("/api")

public class RecommendationController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RecommendationService recommendationService;

    @Operation(summary = "Get Recommendations", description = "Get All Recommendations by Pages", tags = { "recommendations" })
    @GetMapping("/recommendations")
    public Page<RecommendationResource> getAllRecommendations(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Recommendation> postsPage = recommendationService.getAllRecommendations(pageable);
        List<RecommendationResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Recommendation by Id", description = "Get a Recommendations by specifying Id", tags = { "recommendations" })
    @GetMapping("/recommendations/{id}")
    public RecommendationResource getRecommendationById(
            @Parameter(description="Recommendations Id")
            @PathVariable(name = "id") Long recommendationId) {
        return convertToResource(recommendationService.getRecommendationById(recommendationId));
    }

    @GetMapping("/users/{userId}/recommendations")
    public Page<RecommendationResource> getAllRecommendationsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Recommendation> promoPage = recommendationService.getAllRecommendationsByUserId(userId, pageable);
        List<RecommendationResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/recommendations/{recommendationId}")
    public RecommendationResource getRecommendationByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                           @PathVariable(name = "recommendationId") Long recommendationId) {
        return convertToResource(recommendationService.getRecommendationByIdAndUserId(userId, recommendationId));
    }


    @Operation(summary = "Create Recommendations ", description = "Create a Recommendation ", tags = { "recommendations" })
    @PostMapping("/recommendations")
    public RecommendationResource createRecommendation(@RequestBody @Valid SaveRecommendationResource resource)  {
        Recommendation recommendation = convertToEntity(resource);
        return convertToResource(recommendationService.createRecommendation(recommendation));
    }

    @Operation(summary = "Update Recommendation by Id", description = "Update a Recommendation by specifying Id", tags = { "recommendations" })
    @PutMapping("/recommendations/{id}")
    public RecommendationResource updateRecommendation(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveRecommendationResource resource) {
        Recommendation recommendation = convertToEntity(resource);
        return convertToResource(recommendationService.updateRecommendation(postId, recommendation));
    }

    @Operation(summary = "Delete Recommendation by Id", description = "Delete a Recommendation by specifying Id", tags = { "recommendations" })
    @DeleteMapping("/recommendations/{id}")
    public ResponseEntity<?> deleteRecommendation(@PathVariable(name = "id") Long recommendationId) {
        return recommendationService.deleteRecommendation(recommendationId);
    }
    private Recommendation convertToEntity(@Valid SaveRecommendationResource resource) {
        return mapper.map(resource, Recommendation.class);
    }

    private RecommendationResource convertToResource(Recommendation entity) {
        return mapper.map(entity, RecommendationResource.class);
    }
}