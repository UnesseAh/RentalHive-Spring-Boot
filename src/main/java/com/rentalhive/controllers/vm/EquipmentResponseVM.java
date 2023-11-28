package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Equipment;

public record EquipmentResponseVM(
        Long id,
        String name,
        String serialNumber,
        String familyName,
        String modelName,
        String condition,
        String color,
        String fuel,
        Double price,
        String description
      ) {
    public static EquipmentResponseVM fromEquipment(Equipment equipment){
        return new EquipmentResponseVM(
                equipment.getId(),
                equipment.getName(),
                equipment.getSerialNumber(),
                equipment.getModel().getFamily().getName(),
                equipment.getModel().getName(),
                equipment.getVehicleCondition().name(),
                equipment.getColor(),
                equipment.getFuel().name(),
                equipment.getPrice(),
                equipment.getDescription()
        );
    }
}
