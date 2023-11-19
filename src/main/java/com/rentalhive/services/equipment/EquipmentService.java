package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;

import java.util.List;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Long equipmentId, Equipment equipment);
    void deleteEquipment(Long id);
    Equipment getEquipmentById(Long id);
    List<Equipment> getAllEquipments();
    Equipment findEquipmentByName(String name);
    Equipment searchEquipmentsBySerialNumber(String serialNumber);
    List<Equipment> searchEquipmentsByModel(Model model);
    List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice);

}
