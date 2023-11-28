package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;
import com.rentalhive.models.enums.EquipmentCondition;
import com.rentalhive.models.enums.EquipmentFuel;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record EquipmentRequestVM(
        @Digits(integer = 10, fraction = 2, message = "price must be a number")
        Double price,
        @NotBlank(message = "name cannot be null")
        String name,
        @NotBlank(message = "model Name cannot be null")
        String modelName,
        @NotBlank(message = "family name cannot be null")
        String familyName,
        @NotBlank(message = "serial number cannot be null")
        String serialNumber,
        @NotBlank(message = "vehicle condition cannot be null")
        @Pattern(regexp = "^(NEW|USED)$", message = "vehicle condition must be new or used")
        String condition,
        String color,
        String fuel,
        @NotBlank(message = "description cannot be null")
        String description
) {
        public Equipment toEquipment(){
                return new Equipment().builder()
                        .price(price)
                        .name(name)
                        .serialNumber(serialNumber)
                        .description(description)
                        .vehicleCondition(EquipmentCondition.valueOf(condition))
                        .color(color)
                        .fuel(EquipmentFuel.valueOf(fuel))
                        .model(Model.builder()
                                .name(modelName)
                                .family(Family.builder()
                                        .name(familyName)
                                        .build())
                                .build())
                        .build();
        }

}
