package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.CountEquipmentDemandDTO;
import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.services.equipement.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EquipmentController {
    EquipmentService equipmentService;
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @GetMapping("/equipements")
    public ResponseEntity<ResponseMessage> getEquipements(@RequestBody EquipmentSearchDTO equipmentSearchDTO){
        List<EquipmentResponseDTO> equipments = equipmentService.getEquipements(equipmentSearchDTO);
        if(equipments.isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseMessage(400,equipments,"No Equipments Found"));
        }
        else{
            return ResponseEntity.ok().body(new ResponseMessage(200,equipments,"Equipments Found"));
    }

    }

}
