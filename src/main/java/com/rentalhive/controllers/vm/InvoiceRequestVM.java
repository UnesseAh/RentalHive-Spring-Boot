package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Invoice;

public record InvoiceRequestVM(
        String address,
        String bankDetails,
        String companyName
) {
    public Invoice toInvoice() {
        return Invoice.builder()
                .address(address)
                .bankDetails(bankDetails)
                .companyName(companyName)
                .build();
    }
}
