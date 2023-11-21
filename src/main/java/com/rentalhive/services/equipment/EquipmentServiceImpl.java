package com.rentalhive.services.equipment;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import com.rentalhive.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentDemandRepository equipmentDemandRepository;
    private final ModelRepository modelRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentDemandRepository equipmentDemandRepository, ModelRepository modelRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentDemandRepository = equipmentDemandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<EquipmentResponseDTO> getEquipments(@Valid EquipmentSearchDTO equipmentSearchDTO) {
        if(equipmentSearchDTO.endDate() != null || equipmentSearchDTO.startDate() != null){
            List<EquipmentDemand> equipmentDemand = equipmentDemandRepository.getEquipmentDemandByFamilyModelAndRange(equipmentSearchDTO.familyName(),equipmentSearchDTO.modelName(),equipmentSearchDTO.startDate(),equipmentSearchDTO.endDate());
            List<Equipment> equipments = equipmentRepository.getEquipmentByFamilyNameAndModelName(equipmentSearchDTO.familyName(),equipmentSearchDTO.modelName());
            List<Equipment> availableEquipments = new ArrayList<>();
            equipments.forEach(equipment -> {
                boolean isAvailable = true;
                for (EquipmentDemand demand: equipmentDemand){
                    if (demand.getEquipment().getId() == equipment.getId()){ isAvailable = false;break;}
                }
                if (isAvailable){availableEquipments.add(equipment);}
            });
            return availableEquipments.stream().map(eq -> EquipmentResponseDTO.fromEquipment(eq)).toList();
        }
        else {
            List<Equipment> equipments = new ArrayList<>();
            equipments.addAll(equipmentRepository.getEquipmentByFamilyNameAndModelName(equipmentSearchDTO.familyName(),equipmentSearchDTO.modelName()));
            return equipments.stream().map(eq -> EquipmentResponseDTO.fromEquipment(eq)).toList();
        }
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        validateEquipment(equipment);
        Equipment existingEquipment = equipmentRepository.findByName(equipment.getName());
        if(existingEquipment != null){
            throw new IllegalStateException("Equipment with the same name already exist!");
        }
        Optional<Model> optionalModel = modelRepository.findById(equipment.getModel().getId());
        Model equipmentModel = optionalModel.orElseThrow(() -> new ResourceNotFoundException("Model is not valid"));
        if (equipmentModel == null ){
            throw new ResourceNotFoundException("Model is not valid");
        }
        String serialNumber = generateUniqueSerialNumber();
        equipment.setSerialNumber(serialNumber);
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Long equipmentId, Equipment equipmentDetails) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(()->new ResourceNotFoundException("Equipment you want to update doesn't exist"));
        validateEquipment(equipmentDetails);
        Optional<Model> optionalModel = modelRepository.findById(equipmentDetails.getModel().getId());
        Model equipmentModel = optionalModel.orElseThrow(() -> new ResourceNotFoundException("Model is not valid"));
        if (equipmentModel == null ){
            throw new ResourceNotFoundException("Model is not valid");
        }
        String serialNumber = generateUniqueSerialNumber();
        equipment.setName(equipmentDetails.getName());
        equipment.setDescription(equipmentDetails.getDescription());
        equipment.setPrice(equipmentDetails.getPrice());
        equipment.setModel(equipmentDetails.getModel());
        equipment.setSerialNumber(serialNumber);
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(Long id) {
        if(equipmentRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No equipment exist with id : " + id);
        }
        equipmentRepository.deleteById(id);
    }

    @Override
    public Optional<Equipment> getEquipmentById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if(equipment.isEmpty()){
            throw new ResourceNotFoundException("No equipment exist with id : " + id);
        }
        return equipment;
    }

    @Override
    public Optional<Equipment> findEquipmentByName(String name) {
        Optional<Equipment> equipment = Optional.ofNullable(equipmentRepository.findByName(name));
        if(equipment.isEmpty()){
            throw new ResourceNotFoundException("No equipment exist with name : " + name);
        }
        return equipment;
    }

    @Override
    public Optional<Equipment> searchEquipmentsBySerialNumber(String serialNumber) {
        Optional<Equipment> equipment = Optional.ofNullable(equipmentRepository.findBySerialNumber(serialNumber));
        if(equipment.isEmpty()){
            throw new ResourceNotFoundException("No equipment exist with serial number : " + serialNumber);
        }
        return equipment;
    }

    @Override
    public List<Equipment> searchEquipmentsByModel(Model model) {
        List<Equipment> equipment = equipmentRepository.findByModel(model);
        if(equipment.isEmpty()){
            throw new ResourceNotFoundException("No equipment exist with model : " + model.getName());
        }
        return equipment;
    }

    @Override
    public List<Equipment> searchEquipmentsByPriceRange(Double miniPrice, Double maxPrice) {
        return equipmentRepository.findByPriceBetween(miniPrice, maxPrice);
    }

    public String generateUniqueSerialNumber() {
        return UUID.randomUUID().toString();
    }

    public void validateEquipment(Equipment equipment) {
        if (equipment.getName() == null || equipment.getName().isBlank()) {
            throw new IllegalStateException("Equipment name is blank or null");
        }
        if (equipment.getDescription() == null || equipment.getDescription().isBlank()) {
            throw new IllegalStateException("Equipment description is blank or null");
        }
        if (equipment.getPrice() == null) {
            throw new IllegalStateException("Equipment price is null");
        }

        try {
            double price = Double.parseDouble(String.valueOf(equipment.getPrice()));
            if (price < 0) {
                throw new IllegalStateException("Equipment price is not valid");
            }
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Equipment price is not a valid number");
        }
    }
}
