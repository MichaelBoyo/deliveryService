package com.boyo.deliveryservice.controllers;

import com.boyo.deliveryservice.dtos.RegistrationRequest;
import com.boyo.deliveryservice.models.Courier;
import com.boyo.deliveryservice.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request, @RequestParam String pin){
        userService.register(request, pin);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegistrationRequest request){
        userService.registerAdmin(request);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/registerCourier")
    public ResponseEntity<?> registerCourier(@RequestBody Courier courier){
        userService.registerCourier(courier);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

}