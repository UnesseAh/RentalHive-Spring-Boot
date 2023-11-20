package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Equipment;

public record CountEquipmentDemandDTO(
        Long equipmentid,
        Integer count
) {
}
