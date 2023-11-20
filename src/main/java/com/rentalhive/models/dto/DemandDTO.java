package com.rentalhive.models.dto;
import com.rentalhive.models.entities.EquipmentDemand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class DemandDTO {



        private Long id;

        @NotNull(message = "Equipment demands are required")
        private List<EquipmentDemand> equipmentDemands;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

    public void setId(Long id) {
            this.id = id;
        }
    public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }


    }


