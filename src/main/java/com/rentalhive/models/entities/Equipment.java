package com.rentalhive.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentalhive.models.enums.EquipmentCondition;
import com.rentalhive.models.enums.EquipmentFuel;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 10, fraction = 2, message = "Price must be a numeric value with up to 2 decimal places")
    @PositiveOrZero(message = "Price must be a positive or zero value")
    private Double price;
    private String serialNumber;
    @NotBlank(message = "Name is required")
    private String name;
    private String color;
    private EquipmentCondition vehicleCondition;
    private EquipmentFuel fuel;
    @NotBlank(message = "Description is required")
    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String description;
    @NotNull(message = "Model is required")
    @ManyToOne
    private Model model;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

}
