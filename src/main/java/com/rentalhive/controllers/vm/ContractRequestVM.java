package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Contract;
import com.rentalhive.models.entities.Demand;

public record ContractRequestVM(
        String description,
        Long demand_id,
        InvoiceRequestVM invoice

) {
    public Contract toContract() {
        return Contract.builder()
                .description(description)
                .demand(Demand.builder().id(demand_id).build())
                .invoice(invoice.toInvoice())
                .build();
    }

}
