package com.acme.wheelmanager.resource;

import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResource  extends AuditModel {
    private Long id;
    private String name;
    private String last_name;
    private String gender;
}
