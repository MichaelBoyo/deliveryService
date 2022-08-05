package com.boyo.deliveryservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Package {
    @Id
    private String packageID;
    private String packageName;
    private DeliveryStatus deliveryStatus;
    @DBRef
    private Invoice invoice;
}
