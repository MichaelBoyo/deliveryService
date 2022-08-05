package com.boyo.deliveryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String customerName;
    private String address;
    private String phoneNumber;
}
