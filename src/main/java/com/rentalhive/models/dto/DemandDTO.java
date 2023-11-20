package com.rentalhive.models.dto;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public record DemandDTO(
        String title,

        String description,

        UserDTO userDTO,

        LocalDateTime createdAt,

        LocalDateTime modifiedAt
) {


    public static DemandDTO fromDemand(Demand demand) {
        return new DemandDTO(demand.getTitle(),demand.getDescription(),UserDTO.fromUser(demand.getUser()),demand.getCreatedAt(),demand.getModifiedAt());
    }
    public Demand toDemand() {
        return new Demand().builder()
                .title(this.title)
                .description(this.description)
                .user(this.userDTO().toUser())
                .createdAt(LocalDateTime.now())
                .build();
    }
}


