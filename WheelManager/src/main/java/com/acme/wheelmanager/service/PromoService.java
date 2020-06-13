package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Promo;
import com.acme.wheelmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PromoService {
    Page<Promo> getAllPromosByCorporationId(Long corporationId, Pageable pageable);
    Promo getPromoByIdAndCorporationId(Long promoId, Long corporationId);
    ResponseEntity<?> deletePromo(Long promoId,Long corporationId);
    Promo updatePromo(Long corporationId,Long promoId,  Promo promoDetails);
    Promo createPromo(Long corporationId,Promo promo);
    Promo getPromoById(Long promoId);
    Page<Promo> getAllPromos(Pageable pageable);
    Page<Promo> getAllPromosByUserId(Long userId, Pageable pageable);
}