package com.boyo.deliveryservice.models;

import com.boyo.deliveryservice.dtos.UserResponse;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class Shipment {
    @Id
    private String shipmentID;
    private UserResponse sender;
    private UserResponse receiver;
    private DeliveryStatus deliveryStatus;
    private String trackingNo;
    @DBRef
    private Invoice invoice;
    private List<Package> packages = new ArrayList<>();


}
