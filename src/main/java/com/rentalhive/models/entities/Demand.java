package com.rentalhive.models.entities;
import com.rentalhive.models.enums.DemandStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Demand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "demand")
    private List<EquipmentDemand> equipmentDemands;
    @OneToMany(mappedBy = "demand")
    private List<Quote> quotes;
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne(mappedBy = "demand")
    private Contract contract;
    private DemandStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
