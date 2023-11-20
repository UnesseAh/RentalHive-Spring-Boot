package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.enums.Status;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public record DemandRequestDTO(
        @NotNull(message = "Title cannot be null")
        String title,
        @NotNull(message = "Description cannot be null")
        String description,
        @Valid
        UserDTO user,
        @Valid
        List<EquipmentDemandRequestDTO> equipmentDemands
) {
    public Demand toDemand(){
        return new Demand().builder()
                .title(title)
                .description(description)
                .user(user.toUser())
                .status(Status.PENDING)
                .build();

    }
}
