package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Coordinate;
import com.acme.wheelmanager.resource.CoordinateResource;
import com.acme.wheelmanager.resource.SaveCoordinateResource;
import com.acme.wheelmanager.service.CoordinateService;
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

@Tag(name = "coordinates", description = "Coordinates API")
@RestController
@RequestMapping("/api")

public class CoordinateController {

    @Autowired
    private ModelMapper mapper;
 
    @Autowired
    private CoordinateService coordinateService;

    @Operation(summary = "Get Coordinates", description = "Get All Coordinates by Pages", tags = { "coordinates" })
    @GetMapping("/coordinates")
    public Page<CoordinateResource> getAllCoordinates(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Coordinate> postsPage = coordinateService.getAllCoordinates(pageable);
        List<CoordinateResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Coordinate by Id", description = "Get a Coordinates by specifying Id", tags = { "coordinates" })
    @GetMapping("/coordinates/{id}")
    public CoordinateResource getCoordinateById(
            @Parameter(description="Coordinates Id")
            @PathVariable(name = "id") Long coordinateId) {
        return convertToResource(coordinateService.getCoordinateById(coordinateId));
    }

    @GetMapping("/districts/{districtId}/coordinates")
    public Page<CoordinateResource> getAllCoordinatesByDistrictId(
            @PathVariable(name = "districtId") Long districtId,
            Pageable pageable) {
        Page<Coordinate> promoPage = coordinateService.getAllCoordinatesByDistrictId(districtId, pageable);
        List<CoordinateResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/districts/{districtId}/coordinates/{coordinateId}")
    public CoordinateResource getCoordinateByIdAndDistrictId(@PathVariable(name = "districtId") Long districtId,
                                                                 @PathVariable(name = "coordinateId") Long coordinateId) {
        return convertToResource(coordinateService.getCoordinateByIdAndDistrictId(districtId, coordinateId));
    }


    @Operation(summary = "Create Coordinates ", description = "Create a Coordinate ", tags = { "coordinates" })
    @PostMapping("/coordinates")
    public CoordinateResource createCoordinate(@RequestBody @Valid SaveCoordinateResource resource)  {
        Coordinate coordinate = convertToEntity(resource);
        return convertToResource(coordinateService.createCoordinate(coordinate));
    }

    @Operation(summary = "Update Coordinate by Id", description = "Update a Coordinate by specifying Id", tags = { "coordinates" })
    @PutMapping("/coordinates/{id}")
    public CoordinateResource updateCoordinate(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveCoordinateResource resource) {
        Coordinate coordinate = convertToEntity(resource);
        return convertToResource(coordinateService.updateCoordinate(postId, coordinate));
    }

    @Operation(summary = "Delete Coordinate by Id", description = "Delete a Coordinate by specifying Id", tags = { "coordinates" })
    @DeleteMapping("/coordinates/{id}")
    public ResponseEntity<?> deleteCoordinate(@PathVariable(name = "id") Long coordinateId) {
        return coordinateService.deleteCoordinate(coordinateId);
    }
    private Coordinate convertToEntity(@Valid SaveCoordinateResource resource) {
        return mapper.map(resource, Coordinate.class);
    }

    private CoordinateResource convertToResource(Coordinate entity) {
        return mapper.map(entity, CoordinateResource.class);
    }
}