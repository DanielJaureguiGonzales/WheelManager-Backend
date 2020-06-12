package com.acme.wheelmanager.resource;
import com.acme.wheelmanager.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporationResource extends AuditModel{
    private Long id;
    private int ruc;
    private String name;
    private String address;
    private String phone;
}
