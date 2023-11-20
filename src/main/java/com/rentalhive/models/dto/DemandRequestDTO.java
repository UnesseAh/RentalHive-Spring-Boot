package com.rentalhive.models.dto;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.User;

import java.time.LocalDateTime;

public record DemandRequestDTO(
        String title,

        String description,

        UserDTO userDTO,

        LocalDateTime createdAt,

        LocalDateTime modifiedAt
) {



    public Demand toDemand() {
        return new Demand().builder()
                .title(this.title)
                .description(this.description)
                .createdAt(LocalDateTime.now())
                .user(userDTO.toUser())
                .build();
    }
}


