package com.rentalhive.services.equipment;

import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
    }

    @Override
    public EquipmentService createEquipment(EquipmentService equipment) {
        return null;
    }

    @Override
    public EquipmentService updateEquipment(EquipmentService equipment) {
        return null;
    }

    @Override
    public void deleteEquipment(Long id) {

    }

    @Override
    public EquipmentService getEquipmentById(Long id) {
        return null;
    }

    @Override
    public List<EquipmentService> getAllEquipments() {
        return null;
    }

    @Override
    public EquipmentService findEquipmentByName(String name) {
        return null;
    }

    @Override
    public List<EquipmentService> searchEquipmentsBySerialNumber(String serialNumber) {
        return null;
    }

    @Override
    public List<EquipmentService> searchEquipmentsByModel(Model model) {
        return null;
    }

    @Override
    public List<EquipmentService> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice) {
        return null;
    }
}
