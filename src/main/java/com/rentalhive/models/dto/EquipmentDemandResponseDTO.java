package com.rentalhive.models.dto;

import com.rentalhive.models.entities.EquipmentDemand;

import java.time.LocalDate;

public record EquipmentDemandResponseDTO(
        EquipmentResponseDTO equipment,
        LocalDate startDate,
        LocalDate endDate


) {
    public static EquipmentDemandResponseDTO fromEquipmentDemand(EquipmentDemand equipmentDemand){
        return new EquipmentDemandResponseDTO(
                EquipmentResponseDTO.fromEquipment(equipmentDemand.getEquipment()),
                equipmentDemand.getStartDate(),
                equipmentDemand.getEndDate()
        );
    }

}
