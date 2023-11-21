package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.OfferDTO;
import com.rentalhive.services.offer.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OfferController {

    OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public ResponseEntity<ResponseMessage> GetAllOffers() {
        List<OfferDTO> offers = offerService.getAllOffers();
        if (offers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new ResponseMessage(offers));
    }

    @PostMapping("/offers")
    public ResponseEntity<ResponseMessage> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO offer_dto_created = offerService.saveOffer(offerDTO);
        return ResponseEntity.ok().body(new ResponseMessage(Collections.singletonList(offer_dto_created)));
//        return ResponseEntity.ok().body(new ResponseMessage(offer_dto_created));
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<ResponseMessage> getOfferById(@PathVariable("id") long id) {
        OfferDTO offer = offerService.getOfferById(id);
        if (offer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new ResponseMessage(Collections.singletonList(offer)));
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<ResponseMessage> updateOffer(@PathVariable("id") long id, @RequestBody OfferDTO offerDTO) {
        OfferDTO offer = offerService.updateOffer(offerDTO);
        if (offer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new ResponseMessage(Collections.singletonList(offer)));
    }

//    @DeleteMapping("/offers/{id}")
//    public ResponseEntity<ResponseMessage> deleteOffer(@PathVariable("id") long id) {
//        boolean deleted = offerService.deleteOffer(id);
//        if (!deleted) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(new ResponseMessage(Collections.singletonList("Offer deleted successfully")));
//    }


}
