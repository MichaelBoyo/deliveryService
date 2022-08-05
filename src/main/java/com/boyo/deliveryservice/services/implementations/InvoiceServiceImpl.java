package com.boyo.deliveryservice.services.implementations;

import com.boyo.deliveryservice.models.Invoice;
import com.boyo.deliveryservice.repositories.InvoiceRepository;
import com.boyo.deliveryservice.services.interfaces.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
