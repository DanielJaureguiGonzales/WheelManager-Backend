package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.Promo;
import com.acme.wheelmanager.model.User;
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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoRepository promoRepository;

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public User assignUserPromo(Long userId, Long promoId) {
        Promo promo = promoRepository.findById(promoId)
                .orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
        return userRepository.findById(userId).map(user -> {
            if(!user.getPromos().contains(promo)) {
                user.getPromos().add(promo);
                return userRepository.save(user);
            }
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User unassignUserPromo(Long userId, Long promoId) {
        Promo promo = promoRepository.findById(promoId)
                .orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
        return userRepository.findById(userId).map(user -> {
            user.getPromos().remove(promo);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getAllUsersByPromoId(Long promoId, Pageable pageable) {
        return promoRepository.findById(promoId).map(promo -> {
            List<User> users = promo.getUsers();
            int usersCount = users.size();
            return new PageImpl<>(users, pageable, usersCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
    }
}
