package com.rentalhive.services.offer;

import com.rentalhive.models.dto.OfferDTO;
import java.util.List;

public interface OfferService {

    public OfferDTO getOfferById(Long id);
    public OfferDTO saveOffer(OfferDTO offer);
    public OfferDTO updateOffer(OfferDTO offer);
    public boolean deleteOffer(Long id);
    public List<OfferDTO> getAllOffers();
}
