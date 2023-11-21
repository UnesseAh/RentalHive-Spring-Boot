package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import com.rentalhive.services.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public Equipment createEquipment(@RequestBody @Valid Equipment equipment){
        return equipmentService.createEquipment(equipment);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getEquipments(@RequestBody EquipmentSearchDTO equipmentSearchDTO){
        List<EquipmentResponseDTO> equipments = equipmentService.getEquipments(equipmentSearchDTO);
        if(equipments.isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseMessage(400,equipments,"No Equipments Found"));
        }
        else{
            return ResponseEntity.ok().body(new ResponseMessage(200,equipments,"Equipments Found"));
        }
    }


    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable(value = "id") Long id, @RequestBody @Valid Equipment equipment){
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable(value = "id") Long id) {
        equipmentService.deleteEquipment(id);
    }

    @GetMapping("/{name}")
    public Optional<Equipment> findEquipmentByName(@PathVariable(value = "name") String name){
        return equipmentService.findEquipmentByName(name);
    }

    @GetMapping("/{serial-number}")
    public Optional<Equipment> findEquipmentBySerialNumber(@PathVariable(value = "serial-number") String serialNumber){
        return equipmentService.searchEquipmentsBySerialNumber(serialNumber);
    }

    @GetMapping("/{model}")
    public List<Equipment> findEquipmentByModel(@PathVariable(value = "model") @RequestBody Model model){
        return equipmentService.searchEquipmentsByModel(model);
    }
}
