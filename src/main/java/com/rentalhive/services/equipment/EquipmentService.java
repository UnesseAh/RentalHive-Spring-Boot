package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Model;

import java.util.List;

public interface EquipmentService {
    EquipmentService createEquipment(EquipmentService equipment);
    EquipmentService updateEquipment(EquipmentService equipment);
    void deleteEquipment(Long id);
    EquipmentService getEquipmentById(Long id);
    List<EquipmentService> getAllEquipments();
    EquipmentService findEquipmentByName(String name);
    List<EquipmentService> searchEquipmentsBySerialNumber(String serialNumber);
    List<EquipmentService> searchEquipmentsByModel(Model model);
    List<EquipmentService> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice);

}
