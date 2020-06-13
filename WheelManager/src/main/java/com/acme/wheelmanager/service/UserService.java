package com.acme.wheelmanager.service;

import com.acme.wheelmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User assignUserPromo(Long userId, Long promoId);
    User unassignUserPromo(Long userId, Long promoId);
    Page<User> getAllUsersByPromoId(Long promoId, Pageable pageable);
    ResponseEntity<?> deleteUser(Long userId);
    User updateUser(Long userId, User userRequest);
    User createUser(User user);
    User getUserById(Long userId);
    Page<User> getAllUsers(Pageable pageable);
}

