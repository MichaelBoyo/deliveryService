package com.boyo.deliveryservice.services.interfaces;

import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.models.Shipment;

public interface ShipmentService {
    Shipment addShipment(Shipment shipment);

    void addPackage(String shipmentId, Package pack);

    Shipment getShipment(String shipmentID);
}
