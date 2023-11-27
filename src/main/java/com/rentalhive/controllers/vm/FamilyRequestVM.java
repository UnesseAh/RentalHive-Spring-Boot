package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Family;

import javax.validation.constraints.NotBlank;

public record FamilyRequestVM(
        @NotBlank(message = "Name is required")
        String name
) {
    public Family toFamily() {
        return Family.builder()
                .name(name)
                .build();
    }
}
