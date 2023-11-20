package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Equipment;

public record EquipmentResponseDTO(
        String familyName,
        Double price,
        String name,
        String modelName,
        String serialNumber,
        String description
      ) {
    public static EquipmentResponseDTO fromEquipment(Equipment equipment){
        return new EquipmentResponseDTO(
                equipment.getModel().getFamily().getName(),
                equipment.getPrice(),
                equipment.getName(),
                equipment.getModel().getName(),
                equipment.getSerialNumber(),
                equipment.getDescription()
        );
    }
}
