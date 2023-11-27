package com.rentalhive.controllers.vm;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record ContractVM(
        @NotBlank(message = "Id is required")
        Long id,
        @NotBlank(message = "Demand is required")
        int demand,
        @NotBlank(message = "Quote is required")
        double quote,
        @NotBlank(message = "Description is required")
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static ContractVM fromOffer(ContractVM offer) {
        return new ContractVM(offer.id(), offer.demand(), offer.quote(), offer.description(), offer.createdAt(), offer.modifiedAt());
    }
    public ContractVM toOffer() {
        return new ContractVM(this.id(), this.demand(), this.quote(), this.description(), LocalDateTime.now(), LocalDateTime.now());
    }
}
