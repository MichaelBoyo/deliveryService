package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.TransactionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory, String> {
}
