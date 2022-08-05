package com.boyo.deliveryservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document
public class User {
    @Id
    private String customerID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    @DBRef
    private List<Package> sentPackages = new ArrayList<>();
    @DBRef
    private List<Package> receivedPackages = new ArrayList<>();

    @DBRef
    private Account account;

    private Set<Role> roles;

    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
