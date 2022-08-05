package com.boyo.deliveryservice.repositories;

import com.boyo.deliveryservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
