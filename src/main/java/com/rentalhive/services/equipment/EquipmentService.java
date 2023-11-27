package com.rentalhive.services.equipment;

import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Long equipmentId, Equipment equipment);
    void deleteEquipment(Long id);
    Optional<Equipment> getEquipmentById(Long id);
    Equipment  getAllEquipments(Equipment equipment);
    Optional<Equipment> findEquipmentByName(String name);
    Optional<Equipment> searchEquipmentsBySerialNumber(String serialNumber);
    List<Equipment> searchEquipmentsByModel(Model model);
    List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice);

}
