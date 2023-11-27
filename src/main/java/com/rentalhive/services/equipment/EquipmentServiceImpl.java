package com.rentalhive.services.equipment;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import com.rentalhive.repositories.ModelRepository;
import com.rentalhive.services.model.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ModelService modelService;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, ModelService modelService) {
        this.equipmentRepository = equipmentRepository;
        this.modelService = modelService;
    }
    @Override
    public Equipment createEquipment(Equipment equipment) {
        Model model = modelService.getModelByNameAndFamilyName(equipment.getModel().getName(), equipment.getModel().getFamily().getName());
        if(model == null){
            throw new ResourceNotFoundException("Model name: " + equipment.getModel().getName() + "or Family name:"+equipment.getModel().getFamily().getName()+ " not found");
        }
        equipment.setModel(model);
        return equipmentRepository.save(equipment);
    }
    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipment id: " + id + " not found"));
    }
    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
    @Override
    public Equipment updateEquipment(Long id,Equipment equipment) {
        getEquipmentById(id);
        Model model = modelService.getModelByNameAndFamilyName(equipment.getModel().getName(), equipment.getModel().getFamily().getName());
        if(model == null){
            throw new ResourceNotFoundException("Model name: " + equipment.getModel().getName() + "or Family name:"+equipment.getModel().getFamily().getName()+ " not found");
        }
        equipment.setId(id);
        equipment.setModel(model);
        return equipmentRepository.save(equipment);
    }
    @Override
    public void deleteEquipment(Long id) {
        if(id == null){
            throw new ResourceNotFoundException("Equipment id: " + id + " not found");
        }
        equipmentRepository.deleteById(id);
    }
    @Override
    public List<Equipment> getAllEquipmentByModel(String modelName) {
        return equipmentRepository.findAllByModelName(modelName);
    }
    @Override
    public List<Equipment> getAllEquipmentByFamily(String familyName) {
        return equipmentRepository.findAllByModelFamilyName(familyName);
    }
    @Override
public List<Equipment> getAllEquipmentByFamilyAndModel(String familyName, String modelName) {
        if(familyName == null || modelName == null){
            throw new ResourceNotFoundException("Family name: " + familyName + " or model name: " + modelName + " is null");
        }
        return equipmentRepository.getEquipmentByFamilyNameAndModelName(familyName, modelName);
    }
    @Override
    public Equipment getEquipmentBySerialNumber(String serialNumber) {
        if(serialNumber == null){
            throw new ResourceNotFoundException("Serial number is required");
        }
        return equipmentRepository.findEquipmentBySerialNumber(serialNumber);
    }


}
