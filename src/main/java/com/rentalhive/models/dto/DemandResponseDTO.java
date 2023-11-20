package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Demand;

import java.util.List;

public record DemandResponseDTO(
        String title,
        String description,
        UserDTO user,
        String status,
        List<EquipmentDemandResponseDTO> equipmentDemands
) {
    public static DemandResponseDTO fromDemand(Demand demand){
        return new DemandResponseDTO(
                demand.getTitle(),
                demand.getDescription(),
                UserDTO.fromUser(demand.getUser()),
                demand.getStatus().toString(),
                demand.getEquipmentDemands().stream().map(EquipmentDemandResponseDTO::fromEquipmentDemand).toList()
        );
    }
}
