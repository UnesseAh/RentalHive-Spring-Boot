package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.enums.DemandStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public record DemandRequestVM(
        @NotNull(message = "Title cannot be null")
        String title,
        @NotNull(message = "Description cannot be null")
        String description,
        @Valid
        UserVM user,
        @Valid
        List<EquipmentDemandRequestVM> equipmentDemands
) {
    public Demand toDemand(){
        return new Demand().builder()
                .title(title)
                .description(description)
                .user(user.toUser())
                .status(DemandStatus.IN_REVIEW)
                .build();

    }
}
