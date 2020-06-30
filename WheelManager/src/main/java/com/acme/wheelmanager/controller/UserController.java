package com.acme.wheelmanager.controller;

import com.acme.wheelmanager.model.User;
import com.acme.wheelmanager.resource.SaveUserResource;
import com.acme.wheelmanager.resource.UserResource;
import com.acme.wheelmanager.service.UserService;
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

@Tag(name = "users", description = "Users API")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Get All Users by Pages", tags = { "users" })
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<User> postsPage = userService.getAllUsers(pageable);
        List<UserResource> resources = postsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get User by Id", description = "Get a Users by specifying Id", tags = { "users" })
    @GetMapping("/users/{id}")
    public UserResource getUserById(
            @Parameter(description="User Id")
            @PathVariable(name = "id") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }
    @Operation(summary = "Create User ", description = "Create a User ", tags = { "users" })
    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource)  {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }
    @Operation(summary = "Update User by Id", description = "Update a User by specifying Id", tags = { "users" })
    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Long postId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(postId, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/users/{userId}/promos/{promoId}")
    public UserResource assignUserPromo(@PathVariable(name = "userId") Long userId,
                                      @PathVariable(name = "promoId") Long promoId) {
        return convertToResource(userService.assignUserPromo(userId, promoId));
    }

    @DeleteMapping("/users/{userId}/promos/{promoId}")
    public UserResource unassignUserPromo(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "promoId") Long promoId) {
        return convertToResource(userService.unassignUserPromo(userId, promoId));
    }
    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
