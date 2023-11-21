package com.rentalhive.services.equipment;

import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentDemandRepository equipmentDemandRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentDemandRepository equipmentDemandRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentDemandRepository = equipmentDemandRepository;
    }
    @Override
    public List<EquipmentResponseDTO> getEquipments() {
        return equipmentRepository.findAll().stream().map(EquipmentResponseDTO::fromEquipment).toList();
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Long equipmentId, Equipment equipmentDetails) {
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        equipment.setName(equipmentDetails.getName());
        equipment.setDescription(equipmentDetails.getDescription());
        equipment.setPrice(equipmentDetails.getPrice());
        equipment.setModel(equipmentDetails.getModel());
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).get();
    }

    @Override
    public Equipment findEquipmentByName(String name) {
        return equipmentRepository.findByName(name);
    }

    @Override
    public Equipment searchEquipmentsBySerialNumber(String serialNumber) {
        return equipmentRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<Equipment> searchEquipmentsByModel(Model model) {
        return equipmentRepository.findByModel(model);
    }

    @Override
    public List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice) {
        return equipmentRepository.findByPriceBetween(miniPrice, maxPrice);
    }
}
