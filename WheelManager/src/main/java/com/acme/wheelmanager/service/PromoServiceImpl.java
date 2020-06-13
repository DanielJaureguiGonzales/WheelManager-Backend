package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Promo;
import com.acme.wheelmanager.repository.CorporationRepository;
import com.acme.wheelmanager.repository.PromoRepository;
import com.acme.wheelmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoServiceImpl implements PromoService{

    @Autowired
    private CorporationRepository corporationRepository;
    @Autowired
    private PromoRepository promoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Promo> getAllPromosByCorporationId(Long corporationId, Pageable pageable) {
        return promoRepository.findByCorporationId(corporationId,pageable);
    }

    @Override
    public Promo getPromoByIdAndCorporationId(Long promoId, Long corporationId) {
        return promoRepository.findByIdAndCorporationId(promoId, corporationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Promo not found with Id " + promoId +
                                " and CorporationId " + corporationId));
    }

    @Override
    public ResponseEntity<?> deletePromo(Long promoId, Long corporationId) {
        return promoRepository.findByIdAndCorporationId(promoId, corporationId).map(promo -> {
            promoRepository.delete(promo);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Promo not found with Id " + promoId + " and CorporationId " + corporationId));
    }

    @Override
    public Promo updatePromo(Long corporationId,Long promoId,  Promo promoDetails) {
        if(!corporationRepository.existsById(corporationId))
            throw new ResourceNotFoundException("Corporation", "Id", corporationId);

        return promoRepository.findById(promoId).map(promo -> {
            promo.setName(promoDetails.getName());
            promo.setDescription(promoDetails.getDescription());
            promo.setImage_url(promoDetails.getImage_url());
            return promoRepository.save(promo);
        }).orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
    }

    @Override
    public Promo createPromo(Long corporationId, Promo promo) {
        return corporationRepository.findById(corporationId).map(corporation -> {
            promo.setCorporation(corporation);
            return promoRepository.save(promo);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Corporation", "Id", corporationId));
    }

    @Override
    public Promo getPromoById(Long promoId) {
        return promoRepository.findById(promoId)
                .orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
    }

    @Override
    public Page<Promo> getAllPromos(Pageable pageable) {
        return promoRepository.findAll(pageable);
    }


    @Override
    public Page<Promo> getAllPromosByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Promo> promos = user.getPromos();
            int promosCount = promos.size();
            return new PageImpl<>(promos, pageable, promosCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id",userId));
    }
}
