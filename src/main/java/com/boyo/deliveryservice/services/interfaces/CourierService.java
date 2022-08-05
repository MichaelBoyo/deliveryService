package com.boyo.deliveryservice.services.interfaces;

import com.boyo.deliveryservice.models.Courier;

public interface CourierService {
    Courier addCourier(Courier courier);

    Courier getCourier(String courierID);
}
