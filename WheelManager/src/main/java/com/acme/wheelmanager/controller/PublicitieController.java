package com.acme.wheelmanager.controller;


import com.acme.wheelmanager.model.Publicitie;
import com.acme.wheelmanager.resource.ProductResource;
import com.acme.wheelmanager.resource.PublicitieResource;
import com.acme.wheelmanager.resource.SaveProductResource;
import com.acme.wheelmanager.resource.SavePublicitieResource;
import com.acme.wheelmanager.service.PublicitieService;
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

@RestController
@RequestMapping("/api")
public class PublicitieController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PublicitieService publicitieService;

    @GetMapping("/corporations/{corporationId}/publicities")
    public Page<PublicitieResource> getAllPublicitiesByCorporationId(
            @PathVariable(name = "corporationId") Long corporationId,
            Pageable pageable) {
        Page<Publicitie> publicitiePage = publicitieService.getAllPublicitiesByCorporationId(corporationId, pageable);
        List<PublicitieResource> resources = publicitiePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/corporations/{corporationId}/publicities/{publicitieId}")
    public PublicitieResource getProductByIdAndCorporationById(@PathVariable(name = "corporationId") Long corporationId,
                                                            @PathVariable(name = "publicitieId") Long publicitieId) {
        return convertToResource(publicitieService.getPublicitiesByIdAndCorporationId(corporationId, publicitieId));
    }

    @PostMapping("/corporations/{corporationId}/publicities")
    public PublicitieResource createPublicitie(@PathVariable(name = "corporationId") Long corporationId,
                                         @Valid @RequestBody SavePublicitieResource resource) {
        return convertToResource(publicitieService.createPublicitie(corporationId, convertToEntity(resource)));

    }

    @PutMapping("/corporations/{corporationId}/publicities/{publicitieId}")
    public PublicitieResource updatePublicitie(@PathVariable(name = "corporationId") Long corporationId,
                                         @PathVariable(name = "publicitieId") Long publicitieId,
                                         @Valid @RequestBody SavePublicitieResource resource) {
        return convertToResource(publicitieService.updatePublicitie(corporationId, publicitieId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{corporationId}/publicities/{publicitieId}")
    public ResponseEntity<?> deletePublicitie(@PathVariable(name = "corporationId") Long corporationId,
                                           @PathVariable(name = "productId") Long publicitieId) {
        return publicitieService.deletePublicitie(corporationId, publicitieId);
    }

    private Publicitie convertToEntity(SavePublicitieResource resource) {
        return mapper.map(resource, Publicitie.class);
    }
    private PublicitieResource convertToResource(Publicitie entity) {
        return mapper.map(entity, PublicitieResource.class);
    }
}
