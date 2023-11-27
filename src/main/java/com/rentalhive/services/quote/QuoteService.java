package com.rentalhive.services.quote;

import com.rentalhive.models.entities.Quote;
import com.rentalhive.repositories.QuoteRepository;

public interface QuoteService {
    Quote createQuote(Quote quote);
    Quote getQuoteById(Long id);
    Quote acceptQuote(Long id);
    Quote rejectQuote(Long id);

}
