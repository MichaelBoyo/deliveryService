package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.Courier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends MongoRepository<Courier, String> {

}
