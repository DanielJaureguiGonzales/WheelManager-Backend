package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.UserProfile;
import com.acme.wheelmanager.resource.SaveUserProfileResource;
import com.acme.wheelmanager.resource.UserProfileResource;
import com.acme.wheelmanager.service.UserProfileService;
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

@Tag(name = "userProfiles", description = "UserProfiles API")
@RestController
@RequestMapping("/api")
public class UserProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/userprofile")
    public Page<UserProfileResource> getAllUserProfiles(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<UserProfile> userProfilesPage = userProfileService.getAllUsersProfile(pageable);
        List<UserProfileResource> resources = userProfilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/userprofile/{id}")
    public UserProfileResource getUserProfileById(
            @Parameter(description="UserProfile Id")
            @PathVariable(name = "id") Long userProfileId) {
        return convertToResource(userProfileService.getUserProfileById(userProfileId));
    }

    @PostMapping("/userprofile")
    public UserProfileResource createUserProfile(@Valid @RequestBody SaveUserProfileResource resource)  {
        UserProfile userProfile = convertToEntity(resource);
        return convertToResource(userProfileService.createUserProfile(userProfile));
    }

    @PutMapping("/userprofile/{id}")
    public UserProfileResource updateUserProfile(@PathVariable(name = "id") Long userProfileId, @Valid @RequestBody SaveUserProfileResource resource) {
        UserProfile userProfile = convertToEntity(resource);
        return convertToResource(userProfileService.updateUserProfile(userProfileId, userProfile));
    }

    @DeleteMapping("/userprofile/{id}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable(name = "id") Long userProfileId) {
        return userProfileService.deleteUserProfile(userProfileId);
    }

    private UserProfile convertToEntity(SaveUserProfileResource resource) {
        return mapper.map(resource, UserProfile.class);
    }

    private UserProfileResource convertToResource(UserProfile entity) {
        return mapper.map(entity, UserProfileResource.class);
    }
}
