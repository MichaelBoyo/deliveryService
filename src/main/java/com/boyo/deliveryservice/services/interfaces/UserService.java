package com.boyo.deliveryservice.services.interfaces;

import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.dtos.RegistrationRequest;
import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.models.Courier;
import com.boyo.deliveryservice.models.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    void register(RegistrationRequest request, String pin);

    void registerAdmin(RegistrationRequest request);

    void registerCourier(Courier courier);

    void updateUser(String userID,UpdateUserRequest updateUserRequest);


    User getUserFromDatabase(String userId);

    void save(User user);

    List<UserResponse> getALlUsers();
}