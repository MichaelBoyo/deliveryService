package com.boyo.deliveryservice.services.interfaces;

import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.models.Shipment;

import java.util.List;

public interface ApplicationService {
    Shipment sendParcel(Package package_, String courierID, String senderID, String receiverID, Integer amount);

    void fundAccount(String userId, Integer amount);

    String getBalance(String userId);

    void addPackage(String shipmentId, Package pack);

    void updateUser(String userId, UpdateUserRequest updateUserRequest);

    UserResponse viewUserDetails(String userId);

    List<UserResponse> getAllUsers();

    void deliverGoods(String courierId,String shipmentID);

    void sendPackage(Package package_, String courierID, String senderID, String receiverID, Integer amount);
}
