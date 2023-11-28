package com.rentalhive.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rentalhive.models.enums.Status;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    private List<EquipmentDemand> equipmentDemands;
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private Status status;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
