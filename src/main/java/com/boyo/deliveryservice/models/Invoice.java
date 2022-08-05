package com.boyo.deliveryservice.models;

import com.boyo.deliveryservice.dtos.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Invoice {
    @Id
    private String invoiceID;
    private UserResponse customer;
    private UserResponse sender;
    private UserResponse receiver;

    private Package pack;

    private String packageNAme;
    @DBRef
    private Payment payment;

    public Invoice(UserResponse customer, Payment payment, Package pack) {
        this.customer = customer;
        this.payment = payment;
        this.pack = pack;
    }

    public Invoice(String packageName, UserResponse senderResponse, UserResponse receiverResponse, Payment payment) {
        this.packageNAme =packageName;
        sender = senderResponse;
        receiver= receiverResponse;
        this.payment=payment;
    }
}
