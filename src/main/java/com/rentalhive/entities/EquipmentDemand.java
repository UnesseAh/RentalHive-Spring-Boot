package com.rentalhive.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EquipmentDemand {
    @Id
    private Long id;
    @ManyToOne
    private Demand demand;
    @ManyToOne
    private Equipment equipment;
}
