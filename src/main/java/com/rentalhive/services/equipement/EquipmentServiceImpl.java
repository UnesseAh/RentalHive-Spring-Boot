package com.rentalhive.services.equipement;

import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService{
    EquipmentRepository equipmentRepository;
    EquipmentDemandRepository equipmentDemandRepository;
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentDemandRepository equipmentDemandRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentDemandRepository = equipmentDemandRepository;
    }
    @Override
    public List<EquipmentResponseDTO> getEquipements(@Valid EquipmentSearchDTO equipmentSearchDTO) {
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
}
