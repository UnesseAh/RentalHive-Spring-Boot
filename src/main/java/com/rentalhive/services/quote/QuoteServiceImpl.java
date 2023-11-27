package com.rentalhive.services.quote;

import com.rentalhive.handlers.exceptionHandler.OperationException;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Quote;
import com.rentalhive.models.enums.DemandStatus;
import com.rentalhive.models.enums.QuoteStatus;
import com.rentalhive.repositories.QuoteRepository;
import com.rentalhive.services.demand.DemandService;
import org.springframework.stereotype.Service;
@Service
public class QuoteServiceImpl implements QuoteService{
    private final QuoteRepository quoteRepository;
    private final DemandService demandService;
    public QuoteServiceImpl(QuoteRepository quoteRepository, DemandService demandService) {
        this.quoteRepository = quoteRepository;
        this.demandService = demandService;
    }
    public Quote createQuote(Quote quote) {
        Demand demand = demandService.getDemandById(quote.getDemand().getId());
        if(demand.getStatus()!= DemandStatus.PENDING_NEGOTIATION){
            throw new OperationException("Demand is not in PENDING_NEGOTIATION status to create Quote");
        }
        quote.setDemand(demand);
        quote.setStatus(QuoteStatus.PENDING);
        return quoteRepository.save(quote);
    }
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Quote id: " + id + " not found"));
    }
    public Quote acceptQuote(Long id) {
        Quote quote = getQuoteById(id);
        if(quote.getStatus()!= QuoteStatus.PENDING){
            throw new OperationException("Quote is not in PENDING status to accept");
        }
        Demand demand = quote.getDemand();
        demandService.changeDemandStatus(demand.getId(), DemandStatus.PENDING_CONTRACT_CREATION);
        quote.setStatus(QuoteStatus.ACCEPTED);
        return quoteRepository.save(quote);
    }
    public Quote rejectQuote(Long id){
        Quote quote = getQuoteById(id);
        if(quote.getStatus()!= QuoteStatus.PENDING){
            throw new OperationException("Quote is not in PENDING status to accept");
        }
        quote.setStatus(QuoteStatus.REJECTED);
        return quoteRepository.save(quote);
    }

}
