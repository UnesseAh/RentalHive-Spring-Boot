package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.User;

import java.time.LocalDateTime;

public record DemandResponseDTO(
        String title,

        String description,

        UserDTO userDTO,

        LocalDateTime createdAt,

        LocalDateTime modifiedAt
) {
    public static DemandResponseDTO fromDemand(Demand demand) {
        return new DemandResponseDTO(demand.getTitle(),demand.getDescription(),UserDTO.fromUser(demand.getUser()),demand.getCreatedAt(),demand.getModifiedAt());
    }
}
