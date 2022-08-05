package com.boyo.deliveryservice.services.interfaces;

import com.boyo.deliveryservice.models.TransactionHistory;

public interface TransactionHistoryService {
    TransactionHistory addTransaction(TransactionHistory history);
}
