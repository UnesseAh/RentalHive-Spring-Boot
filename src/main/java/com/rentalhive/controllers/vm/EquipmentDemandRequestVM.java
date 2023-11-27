package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipmentDemandRequestVM(
        @NotNull(message = "EquipmentId cannot be null")
        Long equipmentId,
        LocalDate startDate,
        @NotNull(message = "EndDate cannot be null")
        LocalDate endDate
) {
    public EquipmentDemand toEquipmentDemand(){
        return new EquipmentDemand().builder()
                .equipment(Equipment.builder().id(equipmentId).build())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
