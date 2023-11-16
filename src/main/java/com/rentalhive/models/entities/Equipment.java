package com.rentalhive.models.entities;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String serialNumber;
    private String name;
    private String description;
    @ManyToOne
    private Model model;
    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private List<EquipmentDemand> equipmentDemands;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
