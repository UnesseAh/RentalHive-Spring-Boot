package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipmentDemandRequestDTO(
        @NotNull(message = "EquipmentId cannot be null")
        Long equipmentId,
        @NotNull(message = "StartDate cannot be null")
        LocalDate startDate,
        @NotNull(message = "EndDate cannot be null")
        LocalDate endDate
) {
    public EquipmentDemand toEquipmentDemand(Demand demand, Equipment equipment){
        return new EquipmentDemand().builder()
                .demand(demand)
                .equipment(equipment)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
