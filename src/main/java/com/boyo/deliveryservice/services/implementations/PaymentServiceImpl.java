package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Payment;
import com.boyo.deliveryservice.repositories.PaymentRepository;
import com.boyo.deliveryservice.services.interfaces.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
