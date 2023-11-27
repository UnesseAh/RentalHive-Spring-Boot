package com.rentalhive.controllers.vm;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipmentSearchVM(
        String familyName,
        String modelName,
        @NotNull(message = "Start date is required")
        LocalDate startDate,
        @NotNull(message = "End date is required")
        LocalDate endDate
) {

}
