package com.rentalhive.services.invoice;

import com.rentalhive.models.entities.Invoice;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    void deleteInvoice(Long id);

}
