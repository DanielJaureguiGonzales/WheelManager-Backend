package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Advertising;
import com.acme.wheelmanager.resource.AdvertisingResource;
import com.acme.wheelmanager.resource.SaveAdvertisingResource;
import com.acme.wheelmanager.service.AdvertisingService;
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

@Tag(name = "advertisings", description = "Advertisings API")
@RestController
@RequestMapping("/api")

public class AdvertisingController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdvertisingService advertisingService;

    @Operation(summary = "Get Advertisings", description = "Get All Advertisings by Pages", tags = { "advertisings" })
    @GetMapping("/advertisings")
    public List<AdvertisingResource> getAllAdvertisings(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        List<Advertising> postsPage = advertisingService.getAllAdvertisings();
        List<AdvertisingResource> resources = postsPage.stream().map(this::convertToResource).collect(Collectors.toList());

        return resources;
    }

    @Operation(summary = "Get Advertising by Id", description = "Get a Advertisings by specifying Id", tags = { "advertisings" })
    @GetMapping("/advertisings/{id}")
    public AdvertisingResource getAdvertisingById(
            @Parameter(description="Advertising Id")
            @PathVariable(name = "id") Long advertisingId) {
        return convertToResource(advertisingService.getAdvertisingById(advertisingId));
    }

    @GetMapping("/users/{userId}/advertisings")
    public Page<AdvertisingResource> getAllAdvertisingsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Advertising> promoPage = advertisingService.getAllAdvertisingsByUserId(userId, pageable);
        List<AdvertisingResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/advertisings/{advertisingId}")
    public AdvertisingResource getAdvertisingByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                      @PathVariable(name = "advertisingId") Long advertisingId) {
        return convertToResource(advertisingService.getAdvertisingByIdAndUserId(userId, advertisingId));
    }

    @Operation(summary = "Create Advertising ", description = "Create an Advertising ", tags = { "advertisings" })
    @PostMapping("/advertisings")
    public AdvertisingResource createAdvertising(@Valid @RequestBody SaveAdvertisingResource resource)  {
        Advertising advertising = convertToEntity(resource);
        return convertToResource(advertisingService.createAdvertising(advertising));
    }
    @Operation(summary = "Update Advertising by Id", description = "Update an Advertising by specifying Id", tags = { "advertisings" })
    @PutMapping("/advertisings/{id}")
    public AdvertisingResource updateAdvertising(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveAdvertisingResource resource) {
        Advertising advertising = convertToEntity(resource);
        return convertToResource(advertisingService.updateAdvertising(postId, advertising));
    }

    @Operation(summary = "Delete Advertising by Id", description = "Delete an Advertising by specifying Id", tags = { "advertisings" })
    @DeleteMapping("/advertisings/{id}")
    public ResponseEntity<?> deleteAdvertising(@PathVariable(name = "id") Long advertisingId) {
        return advertisingService.deleteAdvertising(advertisingId);
    }
    private Advertising convertToEntity(SaveAdvertisingResource resource) {
        return mapper.map(resource, Advertising.class);
    }

    private AdvertisingResource convertToResource(Advertising entity) {
        return mapper.map(entity, AdvertisingResource.class);
    }
}
