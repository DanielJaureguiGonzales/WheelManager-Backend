package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.TouristPlace;
import com.acme.wheelmanager.resource.TouristPlaceResource;
import com.acme.wheelmanager.resource.SaveTouristPlaceResource;
import com.acme.wheelmanager.service.TouristPlaceService;
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

@Tag(name = "touristPlaces", description = "TouristPlaces API")
@RestController
@RequestMapping("/api")

public class TouristPlaceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TouristPlaceService touristPlaceService;

    @Operation(summary = "Get TouristPlaces", description = "Get All TouristPlaces by Pages", tags = { "touristPlaces" })
    @GetMapping("/touristPlaces")
    public Page<TouristPlaceResource> getAllTouristPlaces(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<TouristPlace> postsPage = touristPlaceService.getAllTouristPlaces(pageable);
        List<TouristPlaceResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get TouristPlace by Id", description = "Get a TouristPlaces by specifying Id", tags = { "touristPlaces" })
    @GetMapping("/touristPlaces/{id}")
    public TouristPlaceResource getTouristPlaceById(
            @Parameter(description="TouristPlaces Id")
            @PathVariable(name = "id") Long touristPlaceId) {
        return convertToResource(touristPlaceService.getTouristPlaceById(touristPlaceId));
    }

    @GetMapping("/districts/{districtId}/touristPlaces")
    public Page<TouristPlaceResource> getAllTouristPlacesByDistrictId(
            @PathVariable(name = "districtId") Long districtId,
            Pageable pageable) {
        Page<TouristPlace> promoPage = touristPlaceService.getAllTouristPlacesByDistrictId(districtId, pageable);
        List<TouristPlaceResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/districts/{districtId}/touristPlaces/{touristPlaceId}")
    public TouristPlaceResource getTouristPlaceByIdAndDistrictId(@PathVariable(name = "districtId") Long districtId,
                                                             @PathVariable(name = "touristPlaceId") Long touristPlaceId) {
        return convertToResource(touristPlaceService.getTouristPlaceByIdAndDistrictId(districtId, touristPlaceId));
    }


    @Operation(summary = "Create TouristPlaces ", description = "Create a TouristPlace ", tags = { "touristPlaces" })
    @PostMapping("/touristPlaces")
    public TouristPlaceResource createTouristPlace(@RequestBody @Valid SaveTouristPlaceResource resource)  {
        TouristPlace touristPlace = convertToEntity(resource);
        return convertToResource(touristPlaceService.createTouristPlace(touristPlace));
    }

    @Operation(summary = "Update TouristPlace by Id", description = "Update a TouristPlace by specifying Id", tags = { "touristPlaces" })
    @PutMapping("/touristPlaces/{id}")
    public TouristPlaceResource updateTouristPlace(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveTouristPlaceResource resource) {
        TouristPlace touristPlace = convertToEntity(resource);
        return convertToResource(touristPlaceService.updateTouristPlace(postId, touristPlace));
    }

    @Operation(summary = "Delete TouristPlace by Id", description = "Delete a TouristPlace by specifying Id", tags = { "touristPlaces" })
    @DeleteMapping("/touristPlaces/{id}")
    public ResponseEntity<?> deleteTouristPlace(@PathVariable(name = "id") Long touristPlaceId) {
        return touristPlaceService.deleteTouristPlace(touristPlaceId);
    }
    private TouristPlace convertToEntity(@Valid SaveTouristPlaceResource resource) {
        return mapper.map(resource, TouristPlace.class);
    }

    private TouristPlaceResource convertToResource(TouristPlace entity) {
        return mapper.map(entity, TouristPlaceResource.class);
    }
}