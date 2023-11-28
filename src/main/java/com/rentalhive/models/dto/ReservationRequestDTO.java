package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.enums.Status;

import java.util.List;

public record ReservationRequestDTO(
        String title,
        String description,
        List<EquipmentReservationRequestDTO> equipmentReservations
) {
    public Demand toDemand(){
        return new Demand().builder()
                .title(title)
                .description(description)
                .status(Status.PENDING)
                .build();
    }
}
