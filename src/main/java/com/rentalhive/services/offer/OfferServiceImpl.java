package com.rentalhive.services.offer;

import com.rentalhive.models.dto.OfferDTO;
import com.rentalhive.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO getOfferById(Long id) {
        return null;
    }

    @Override
    public OfferDTO saveOffer(OfferDTO offerDTO) {
        return null;
    }

    @Override
    public OfferDTO updateOffer(OfferDTO offer) {
        return null;
    }

    @Override
    public boolean deleteOffer(Long id) {
        return false;
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return null;
    }





}
