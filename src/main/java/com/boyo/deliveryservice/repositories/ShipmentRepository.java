package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.Shipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ShipmentRepository extends MongoRepository<Shipment, String> {
}
