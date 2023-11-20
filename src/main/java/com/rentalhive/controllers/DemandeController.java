package com.rentalhive.controllers;


import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;
import com.rentalhive.services.demand.DemandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DemandeController {
    DemandService demandService;

    public DemandeController(DemandService demandService) {
        this.demandService = demandService;
    }
    @PostMapping("/demand")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody DemandRequestDTO demandRequestDTO) {
        DemandResponseDTO demand_dto_created = demandService.saveDemand(demandRequestDTO);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED.value(), demand_dto_created,HttpStatus.CREATED.toString()), HttpStatus.CREATED);
    }
}
