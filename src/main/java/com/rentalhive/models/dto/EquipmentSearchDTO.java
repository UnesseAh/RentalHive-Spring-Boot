package com.rentalhive.models.dto;

import javax.persistence.Cache;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public record EquipmentSearchDTO(
        String familyName,
        String modelName,
        @NotNull(message = "Start date is required")
        LocalDate startDate,
        @NotNull(message = "End date is required")
        LocalDate endDate
) {

}
