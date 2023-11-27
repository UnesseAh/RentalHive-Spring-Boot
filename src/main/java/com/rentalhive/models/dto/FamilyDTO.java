package com.rentalhive.models.dto;

import com.rentalhive.models.entities.Family;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record FamilyDTO(
        @NotBlank(message = "Family name can not be empty")
        String name,
        String created_at,
        String modified_at
) {
        public static FamilyDTO toFamilyDTO(Family family){
                String createdAt = family.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
                String modifiedAt = family.getModifiedAt().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
                return new FamilyDTO(family.getName(),
                        createdAt,
                        modifiedAt);
        }

        public Family toFamily(){
                return new Family().builder()
                        .name(this.name)
                        .build();
        }
}
