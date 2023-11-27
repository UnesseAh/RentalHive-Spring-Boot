package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Quote;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public record QuoteRequestVM(
        @Digits(integer = 10, fraction = 2, message = "equipment_discount must be a number")
        Double equipment_discount,
        @NotNull(message = "description cannot be null")
        String description,
        @Digits(integer = 10, fraction = 0, message = "demand_id must be a number")
        Long demand_id
) {
   public Quote toQuote() {
       return Quote.builder()
               .equipment_discount(equipment_discount)
               .description(description)
               .demand(Demand.builder().id(demand_id).build())
               .build();
   }
}
