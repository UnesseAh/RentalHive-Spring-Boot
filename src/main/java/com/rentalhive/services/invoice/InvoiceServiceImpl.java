package com.rentalhive.services.invoice;

import com.rentalhive.models.entities.Contract;
import com.rentalhive.models.entities.Invoice;
import com.rentalhive.repositories.InvoiceRepository;
import com.rentalhive.services.contract.ContractService;

public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    private final ContractService contractService;
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ContractService contractService) {
        this.invoiceRepository = invoiceRepository;
        this.contractService = contractService;
    }
    @Override
    public Invoice createInvoice(Invoice invoice) {
        Contract contract = contractService.getContractById(invoice.getContract().getId());
        invoice.setContract(contract);
        return invoiceRepository.save(invoice);
    }
    public
}
