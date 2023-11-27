package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Quote;

public record QuoteResponseVM(
        Long id,
        Double equipment_discount,
        String status,
        String description
) {
    public static QuoteResponseVM fromQuote(Quote quote){
        return new QuoteResponseVM(
                quote.getId(),
                quote.getEquipment_discount(),
                quote.getStatus().toString(),
                quote.getDescription()
        );
    }
}
