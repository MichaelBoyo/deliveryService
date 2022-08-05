package com.boyo.deliveryservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class TransactionHistory {
    @Id
    private String id;
    private BigDecimal amount;
    private TransactionType type;
    private String date;

    public TransactionHistory(BigDecimal amount,
                              TransactionType type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

}
