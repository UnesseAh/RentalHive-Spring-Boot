package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
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
