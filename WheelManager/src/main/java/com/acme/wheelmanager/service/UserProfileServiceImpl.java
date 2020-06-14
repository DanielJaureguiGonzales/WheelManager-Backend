package com.acme.wheelmanager.service;

import com.acme.wheelmanager.exception.ResourceNotFoundException;
import com.acme.wheelmanager.model.User;
import com.acme.wheelmanager.model.UserProfile;
import com.acme.wheelmanager.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public ResponseEntity<?> deleteUserProfile(Long userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", userProfileId));
        userProfileRepository.delete(userProfile);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserProfile updateUserProfile(Long userProfileId, UserProfile userProfileRequest) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile", "Id", userProfileId));
        userProfile.setName(userProfileRequest.getName());
        userProfile.setLast_name(userProfileRequest.getLast_name());
        userProfile.setGender(userProfileRequest.getGender());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserProfileById(Long userProfileId) {
        return userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile", "Id", userProfileId));
    }

    @Override
    public Page<UserProfile> getAllUsersProfile(Pageable pageable) {
        return userProfileRepository.findAll(pageable);
    }
}
