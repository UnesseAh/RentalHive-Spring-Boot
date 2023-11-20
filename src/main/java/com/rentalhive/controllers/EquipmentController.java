package com.rentalhive.controllers;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.services.equipment.EquipmentService;
import com.rentalhive.services.equipment.EquipmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentServiceImpl equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/")
    public List<Equipment> getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    @PostMapping("/")
    public Equipment createEquipment(@RequestBody Equipment equipment){
        return equipmentService.createEquipment(equipment);
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable(value = "id") Long id, Equipment equipment){
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable(value = "id") Long id){
        equipmentService.deleteEquipment(id);
    }
}
