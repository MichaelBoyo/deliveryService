package com.boyo.deliveryservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Courier {
    @Id
    private  String courierID;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private  String address;
    private String phoneNumber;
    @DBRef
    private Account account;
    private String username;
    private String password;
    private List<Shipment> shipments = new ArrayList<>();
    private List<Package> packages = new ArrayList<>();
    private List<Package> undeliveredPackages = new ArrayList<>();
    private List<Package> deliveredPackages = new ArrayList<>();
}
