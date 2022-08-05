package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.dtos.RegistrationRequest;
import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.models.Courier;
import com.boyo.deliveryservice.models.User;
import com.boyo.deliveryservice.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl2 implements UserService {
    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public void register(RegistrationRequest request, String pin) {
        System.out.println("used impl 2");
    }

    @Override
    public void registerAdmin(RegistrationRequest request) {

    }

    @Override
    public void registerCourier(Courier courier) {

    }

    @Override
    public void updateUser(String userID, UpdateUserRequest updateUserRequest) {

    }

    @Override
    public User getUserFromDatabase(String userId) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public List<UserResponse> getALlUsers() {
        return null;
    }
}
