package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.services.equipment.EquipmentService;
import com.rentalhive.services.equipment.EquipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.services.equipment.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment){
        return equipmentService.createEquipment(equipment);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getEquipements(){
        List<EquipmentResponseDTO> equipments = equipmentService.getEquipments();
        if(equipments.isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseMessage(400,equipments,"No Equipments Found"));
        }
        else{
            return ResponseEntity.ok().body(new ResponseMessage(200,equipments,"Equipments Found"));
        }
    }


    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable(value = "id") Long id, @RequestBody Equipment equipment){
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable(value = "id") Long id) {
        equipmentService.deleteEquipment(id);
    }
}
