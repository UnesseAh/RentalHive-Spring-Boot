package com.rentalhive.models.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record OfferDTO(
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
    public static OfferDTO fromOffer(OfferDTO offer) {
        return new OfferDTO(offer.id(), offer.demand(), offer.quote(), offer.description(), offer.createdAt(), offer.modifiedAt());
    }
    public OfferDTO toOffer() {
        return new OfferDTO(this.id(), this.demand(), this.quote(), this.description(), LocalDateTime.now(), LocalDateTime.now());
    }
}
