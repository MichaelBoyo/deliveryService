package com.boyo.deliveryservice.controllers;

import com.boyo.deliveryservice.dtos.UpdateUserRequest;
import com.boyo.deliveryservice.dtos.UserResponse;
import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.models.Shipment;
import com.boyo.deliveryservice.services.interfaces.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class DeliveryServiceController {
    private final ApplicationService applicationService;

    @PostMapping("/send-parcel")
    public Shipment sendParcel(@RequestBody Package package_,
                               @RequestParam String courierID,
                               @RequestParam String senderID,
                               @RequestParam String receiverID,
                               @RequestParam Integer amount) {
        return applicationService.sendParcel(package_, courierID, senderID, receiverID, amount);
    }
    @PatchMapping("sendPackage")
    public ResponseEntity<?> sendPackage(@RequestBody Package package_,
                                         @RequestParam String courierID,
                                         @RequestParam String senderID,
                                         @RequestParam String receiverID,
                                         @RequestParam Integer amount){
        applicationService.sendPackage(package_,courierID,senderID,receiverID,amount);
        return new ResponseEntity<>("request successful",HttpStatus.OK);
    }

    @PatchMapping("/fundAccount")
    public ResponseEntity<?> deposit(@RequestParam String userId, @RequestParam Integer amount) {
        applicationService.fundAccount(userId, amount);
        return new ResponseEntity<>(amount + " added to Account Successfully", HttpStatus.OK);
    }

    @GetMapping("/getBalance")
    public String getBalance(@RequestParam String userId) {
        return applicationService.getBalance(userId);
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam String userId, @RequestBody UpdateUserRequest updateUserRequest) {
        applicationService.updateUser(userId, updateUserRequest);
        return new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);
    }

    @PatchMapping("/addPackage")
    public ResponseEntity<?> addPackageToShipment(@RequestParam String shipmentId, @RequestBody Package pack) {
        applicationService.addPackage(shipmentId, pack);
        return new ResponseEntity<>("Package Added Successfully", HttpStatus.OK);
    }

    @GetMapping("/viewUserDetails")
    public UserResponse viewUSer(@RequestParam String userId) {
        return applicationService.viewUserDetails(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/viewAllUsers")
    public List<UserResponse> viewUsers() {
        return applicationService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PatchMapping("/deliverGoods")
    public ResponseEntity<?> deliverPackage(@RequestParam String courierId,
                                            @RequestParam String shipmentID) {
        applicationService.deliverGoods(courierId, shipmentID);
        return new ResponseEntity<>("Shipment delivered Successfully", HttpStatus.OK);
    }

}