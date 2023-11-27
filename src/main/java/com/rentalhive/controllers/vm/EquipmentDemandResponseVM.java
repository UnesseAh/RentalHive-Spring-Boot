package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.EquipmentDemand;

import java.time.LocalDate;

public record EquipmentDemandResponseVM(
        EquipmentResponseVM equipment,
        LocalDate startDate,
        LocalDate endDate


) {
    public static EquipmentDemandResponseVM fromEquipmentDemand(EquipmentDemand equipmentDemand){
        return new EquipmentDemandResponseVM(
                EquipmentResponseVM.fromEquipment(equipmentDemand.getEquipment()),
                equipmentDemand.getStartDate(),
                equipmentDemand.getEndDate()
        );
    }

}
