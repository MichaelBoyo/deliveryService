package com.boyo.deliveryservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Payment {
    @Id
    private String paymentID;
    private Integer amount;
    private LocalDateTime paymentDate;

    public Payment(Integer amount) {
        this.amount = amount;
    }
    public Payment(Integer amount, LocalDateTime paymentDate){
        this.amount=amount;
        this.paymentDate=paymentDate;
    }
}
