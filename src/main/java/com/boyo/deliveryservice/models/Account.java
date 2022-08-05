package com.boyo.deliveryservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Account {
    @Id
    private String id;
    private String username;
    private String pin;

    private String dateCreated;
    @DBRef
    private List<TransactionHistory> transactionHistory = new ArrayList<>();

    public Account(String username, String pin) {
        this.username = username;
        this.pin = pin;
    }

    public boolean pinIsValid(String pin){
        return this.pin.equals(pin);
    }
}
