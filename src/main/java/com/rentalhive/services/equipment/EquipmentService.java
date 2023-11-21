package com.rentalhive.services.equipment;

import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Long equipmentId, Equipment equipment);
    void deleteEquipment(Long id);
    Optional<Equipment> getEquipmentById(Long id);
    List<EquipmentResponseDTO>  getEquipments(EquipmentSearchDTO equipmentSearchDTO);
    Equipment findEquipmentByName(String name);
    Equipment searchEquipmentsBySerialNumber(String serialNumber);
    List<Equipment> searchEquipmentsByModel(Model model);
    List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice);

}
