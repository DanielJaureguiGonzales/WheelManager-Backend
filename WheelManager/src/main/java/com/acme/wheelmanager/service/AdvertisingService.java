package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.Advertising;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdvertisingService {
    Page<Advertising> getAllAdvertisingsByUserId(Long userId, Pageable pageable);
    Advertising getAdvertisingByIdAndUserId(Long advertisingId, Long userId);
    ResponseEntity<?> deleteAdvertising(Long advertisingId);
    Advertising updateAdvertising(Long advertisingId, Advertising advertisingRequest);
    Advertising createAdvertising(Advertising advertisingRequest);
    Advertising getAdvertisingById(Long advertisingId);
    Page<Advertising> getAllAdvertisings(Pageable pageable);
}
