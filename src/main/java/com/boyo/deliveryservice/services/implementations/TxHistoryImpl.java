package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.TransactionHistory;
import com.boyo.deliveryservice.repositories.TransactionHistoryRepository;
import com.boyo.deliveryservice.services.interfaces.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TxHistoryImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository historyRepository;
    @Override
    public TransactionHistory addTransaction(TransactionHistory history) {
        return historyRepository.save(history);
    }
}
