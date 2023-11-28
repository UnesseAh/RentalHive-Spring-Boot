package com.rentalhive.controllers.vm;

public record ContractResponseVM(
        Long id,
        String description,
        InvoiceResponseVM invoice,
        String status

) {
    public static ContractResponseVM fromContract(com.rentalhive.models.entities.Contract contract) {
        return new ContractResponseVM(
                contract.getId(),
                contract.getDescription(),
                InvoiceResponseVM.fromInvoice(contract.getInvoice()),
                contract.getStatus().toString()
        );
    }
}
