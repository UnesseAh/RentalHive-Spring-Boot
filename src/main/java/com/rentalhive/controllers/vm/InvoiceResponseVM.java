package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Invoice;

public record InvoiceResponseVM(
        Long id,
        String address,
        String bankDetails,
        String companyName,
        QuoteResponseVM quote
) {
    public static InvoiceResponseVM fromInvoice(Invoice invoice) {
        return new InvoiceResponseVM(
                invoice.getId(),
                invoice.getAddress(),
                invoice.getBankDetails(),
                invoice.getCompanyName(),
                QuoteResponseVM.fromQuote(invoice.getQuote())
        );
    }
}
