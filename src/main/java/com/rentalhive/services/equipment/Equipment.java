package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Model;

import java.util.List;

public interface Equipment {
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Equipment equipment);
    void deleteEquipment(Long id);
    Equipment getEquipmentById(Long id);
    List<Equipment> getAllEquipments();
    Equipment findEquipmentByName(String name);
    List<Equipment> searchEquipmentsBySerialNumber(String serialNumber);
    List<Equipment> searchEquipmentsByModel(Model model);
    List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice);

}
