package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;
import com.rentalhive.services.demand.DemandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/demands")
public class DemandController {
    DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @PostMapping()
    public ResponseEntity<DemandResponseDTO> createDemand(@Valid @RequestBody DemandRequestDTO demandRequestDTO){
        return ResponseEntity.ok(demandService.makeDemand(demandRequestDTO));
    }
    @GetMapping("/{demandId}/validate")
    public ResponseEntity<ResponseMessage> validateDemand(@PathVariable Long demandId){
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), demandService.validateDemand(demandId)?"Demand is valid":"Demand is not valid"));
    }
    @GetMapping()
    public ResponseEntity getAllDemands(){
        return ResponseEntity.ok(demandService.getAllDemands());
    }


}

