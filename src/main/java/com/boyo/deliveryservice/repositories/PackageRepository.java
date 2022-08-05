package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends MongoRepository<Package, String> {
}
