package com.rentalhive.services.invoice;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.entities.Contract;
import com.rentalhive.models.entities.Invoice;
import com.rentalhive.repositories.InvoiceRepository;
import com.rentalhive.services.contract.ContractService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice id: " + id + " not found"));
    }
    @Override
    public void deleteInvoice(Long id) {
        getInvoiceById(id);
        invoiceRepository.deleteById(id);
    }
}
