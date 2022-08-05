package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Package;
import com.boyo.deliveryservice.repositories.PackageRepository;
import com.boyo.deliveryservice.services.interfaces.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    @Override
    public Package addPackage(Package pack) {
        return packageRepository.save(pack);
    }
}
