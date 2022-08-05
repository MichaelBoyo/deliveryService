package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
