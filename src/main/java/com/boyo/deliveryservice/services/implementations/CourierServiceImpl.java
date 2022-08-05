package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Courier;
import com.boyo.deliveryservice.repositories.CourierRepository;
import com.boyo.deliveryservice.services.interfaces.CourierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    @Override
    public Courier addCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    @Override
    public Courier getCourier(String courierID) {
        return courierRepository.findById(courierID).orElseThrow(
                ()-> new IllegalArgumentException("courier not found"));
    }
}
