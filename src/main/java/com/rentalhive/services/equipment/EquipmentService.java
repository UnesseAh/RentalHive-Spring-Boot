package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Equipment getEquipmentById(Long id);
    List<Equipment> getAllEquipment();
    Equipment updateEquipment(Long id,Equipment equipment);
    void deleteEquipment(Long id);
    List<Equipment> getAllEquipmentByModel(String modelName);
    List<Equipment> getAllEquipmentByFamily(String familyName);
    List<Equipment> getAllEquipmentByFamilyAndModel(String familyName, String modelName);
    Equipment getEquipmentBySerialNumber(String serialNumber);


}
