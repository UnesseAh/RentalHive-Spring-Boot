package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;

import javax.validation.constraints.NotBlank;

public record ModelRequestVM(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Family name is required")
        String familyName
) {
    public Model toModel() {
        return Model.builder()
                .name(name)
                .family(Family.builder()
                        .name(familyName)
                        .build())
                .build();
    }
}
