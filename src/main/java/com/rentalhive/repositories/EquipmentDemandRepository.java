package com.rentalhive.repositories;

import com.rentalhive.models.entities.EquipmentDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDemandRepository  extends JpaRepository<EquipmentDemand, Long> {
}
