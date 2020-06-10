package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Long id;
    private String username;
    private String password;
    private String email;
}
