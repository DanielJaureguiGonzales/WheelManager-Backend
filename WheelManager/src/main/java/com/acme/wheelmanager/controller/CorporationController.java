package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.resource.SaveCorporationResource;
import com.acme.wheelmanager.model.Corporation;
import com.acme.wheelmanager.service.CorporationService;
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
import com.acme.wheelmanager.resource.CorporationResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "corporations", description = "Corporations API")
@RestController
@RequestMapping("/corporation")
public class CorporationController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CorporationService corporationService;

    @Operation(summary = "Get Corporations", description = "Get All Corporations by Pages", tags = { "corporations" })
    @GetMapping("/corporations")
    public Page<CorporationResource> getAllCorporations(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Corporation> postsPage = corporationService.getAllCorporations(pageable);
        List<CorporationResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Corporation by Id", description = "Get a Corporations by specifying Id", tags = { "corporations" })
    @GetMapping("/corporations/{id}")
    public CorporationResource getCorporationById(
            @Parameter(description="Corporation Id")
            @PathVariable(name = "id") Long corporationId) {
        return convertToResource(corporationService.getCorporationById(corporationId));
    }

    @Operation(summary = "Create Corporation ", description = "Create a Corporation ", tags = { "corporations" })
    @PostMapping("/corporations")
    public CorporationResource createCorporation(@Valid @RequestBody SaveCorporationResource resource)  {
        Corporation corporation = convertToEntity(resource);
        return convertToResource(corporationService.createCorporation(corporation));
    }
    @Operation(summary = "Update Corporation by Id", description = "Update a Corporation by specifying Id", tags = { "corporations" })
    @PutMapping("/corporations/{id}")
    public CorporationResource updateCorporation(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveCorporationResource resource) {
        Corporation corporation = convertToEntity(resource);
        return convertToResource(corporationService.updateCorporation(postId, corporation));
    }

    @Operation(summary = "Delete Corporation by Id", description = "Delete a Corporation by specifying Id", tags = { "corporations" })
    @DeleteMapping("/corporations/{id}")
    public ResponseEntity<?> deleteCorporation(@PathVariable(name = "id") Long corporationId) {
        return corporationService.deleteCorporation(corporationId);
    }
    private Corporation convertToEntity(SaveCorporationResource resource) {
        return mapper.map(resource, Corporation.class);
    }

    private CorporationResource convertToResource(Corporation entity) {
        return mapper.map(entity, CorporationResource.class);
    }
}
