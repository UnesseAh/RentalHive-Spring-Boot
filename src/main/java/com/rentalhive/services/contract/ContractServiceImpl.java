package com.rentalhive.services.contract;

import com.rentalhive.handlers.exceptionHandler.OperationException;
import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.entities.Contract;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Invoice;
import com.rentalhive.models.entities.Quote;
import com.rentalhive.models.enums.ContractStatus;
import com.rentalhive.models.enums.QuoteStatus;
import com.rentalhive.repositories.ContractRepository;
import com.rentalhive.services.demand.DemandService;
import com.rentalhive.services.invoice.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final InvoiceService invoiceService;
    private final DemandService demandService;
    public ContractServiceImpl(ContractRepository contractRepository, InvoiceService invoiceService, DemandService demandService) {
        this.contractRepository = contractRepository;
        this.invoiceService = invoiceService;
        this.demandService = demandService;
    }
    @Override
    public Contract createContract(Contract contract) {
        Demand demand = demandService.getDemandById(contract.getDemand().getId());
        if(demand.getQuotes()==null || demand.getQuotes().size()==0){
            throw new OperationException("Demand has no quotes");
        }
        List<Quote> quotes = demand.getQuotes().stream()
                .filter(q ->q.getStatus()== QuoteStatus.ACCEPTED)
                .collect(Collectors.toList());
        if(quotes==null || quotes.size()==0){
            throw new OperationException("Demand has no accepted quotes");
        }
        if (contractRepository.findContractByDemandId(contract.getDemand().getId()) != null){
            throw new OperationException("Contract already exists");
        }
        Invoice invoice = contract.getInvoice();
        invoice.setQuote(quotes.get(0));
        Invoice invoice1 = invoiceService.createInvoice(invoice);
        contract.setStatus(ContractStatus.PENDING);
        contract.setInvoice(invoice1);
        return contractRepository.save(contract);

    }
    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contract id: " + id + " not found"));
    }
    @Override
    public Contract getContractByDemandId(Long demandId) {
        return contractRepository.findContractByDemandId(demandId);
    }
    @Override
    public Contract signContract(Long contractId) {
        Contract contract = getContractById(contractId);
        contract.setStatus(ContractStatus.SIGNED);
        contractRepository.save(contract);
        return contract;
    }


}
