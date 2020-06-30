package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.Promo;
import com.acme.wheelmanager.resource.PromoResource;
import com.acme.wheelmanager.resource.SavePromoResource;
import com.acme.wheelmanager.service.PromoService;
import io.swagger.v3.oas.annotations.Parameter;
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
public class PromoController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PromoService promoService;

    @GetMapping("/promos")
    public Page<PromoResource> getAllPromos(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Promo> promoPage = promoService.getAllPromos(pageable);
        List<PromoResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/promos/{id}")
    public PromoResource getPromoById(
            @Parameter(description="Promo Id")
            @PathVariable(name = "id") Long promoId) {
        return convertToResource(promoService.getPromoById(promoId));
    }

    @GetMapping("/corporations/{corporationId}/promos")
    public Page<PromoResource> getAllPromosByCorporationId(
            @PathVariable(name = "corporationId") Long corporationId,
            Pageable pageable) {
        Page<Promo> promoPage = promoService.getAllPromosByCorporationId(corporationId, pageable);
        List<PromoResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/corporations/{corporationId}/promos/{promoId}")
    public PromoResource getPromoByIdAndCorporationId(@PathVariable(name = "corporationId") Long corporationId,
                                                   @PathVariable(name = "promoId") Long promoId) {
        return convertToResource(promoService.getPromoByIdAndCorporationId(corporationId, promoId));
    }
    @PostMapping("/corporations/{corporationId}/promos")
    public PromoResource createPromo(@PathVariable(name = "corporationId") Long corporationId,
                                         @Valid @RequestBody SavePromoResource resource) {
        return convertToResource(promoService.createPromo(corporationId, convertToEntity(resource)));

    }

    @PutMapping("/corporations/{corporationId}/promos/{promoId}")
    public PromoResource updatePromo(@PathVariable(name = "corporationId") Long corporationId,
                                         @PathVariable(name = "promoId") Long promoId,
                                         @Valid @RequestBody SavePromoResource resource) {
        return convertToResource(promoService.updatePromo(corporationId, promoId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{corporationId}/promos/{promoId}")
    public ResponseEntity<?> deletePromo(@PathVariable(name = "corporationId") Long corporationId,
                                           @PathVariable(name = "promoId") Long promoId) {
        return promoService.deletePromo(corporationId, promoId);
    }

    private Promo convertToEntity(SavePromoResource resource) {
        return mapper.map(resource, Promo.class);
    }

    private PromoResource convertToResource(Promo entity) {
        return mapper.map(entity, PromoResource.class);
    }
}
