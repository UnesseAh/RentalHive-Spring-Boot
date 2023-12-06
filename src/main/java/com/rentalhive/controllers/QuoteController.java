package com.rentalhive.controllers;

import com.rentalhive.controllers.vm.QuoteRequestVM;
import com.rentalhive.controllers.vm.QuoteResponseVM;
import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Quote;

import com.rentalhive.services.quote.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {
    QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;

    }
    @PostMapping
    public ResponseEntity createQuote(@RequestBody QuoteRequestVM quoteRequestVM){
        Quote quote = quoteService.createQuote(quoteRequestVM.toQuote());
        return ResponseMessage.created(QuoteResponseVM.fromQuote(quote),
                "Quote created successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity getQuoteById(@PathVariable Long id){
        Quote quote = quoteService.getQuoteById(id);
        return ResponseMessage.ok(QuoteResponseVM.fromQuote(quote),
                "Quote retrieved successfully");
    }
    @PutMapping("/{id}/accept")
    public ResponseEntity acceptQuote(@PathVariable Long id){
        Quote quote = quoteService.acceptQuote(id);
        return ResponseMessage.ok(QuoteResponseVM.fromQuote(quote),
                "Quote accepted successfully");
    }
    @PutMapping("/{id}/reject")
    public ResponseEntity rejectQuote(@PathVariable Long id){
        Quote quote = quoteService.rejectQuote(id);
        return ResponseMessage.ok(QuoteResponseVM.fromQuote(quote),
                "Quote rejected successfully");
    }
}
