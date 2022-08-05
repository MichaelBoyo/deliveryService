package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.models.Shipment;
import com.boyo.deliveryservice.repositories.ShipmentRepository;
import com.boyo.deliveryservice.services.interfaces.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    @Override
    public Shipment addShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public void addPackage(String shipmentId, Package pack) {
        var shipment = findShipment(shipmentId);
        shipment.getPackages().add(pack);
        shipmentRepository.save(shipment);
    }

    @Override
    public Shipment getShipment(String shipmentID) {
        return shipmentRepository.findById(shipmentID)
                .orElseThrow(()->new IllegalArgumentException("shipment not found"));
    }

    private Shipment findShipment(String shipmentID){
        return shipmentRepository.findById(shipmentID).orElseThrow(()->
                new UsernameNotFoundException("shipment not found"));
    }
}
