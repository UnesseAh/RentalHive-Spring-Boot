package com.rentalhive.repositories;

import com.rentalhive.models.dto.CountEquipmentDemandDTO;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentDemandRepository  extends JpaRepository<EquipmentDemand, Long> {
    @Query("SELECT eqd FROM EquipmentDemand eqd WHERE (eqd.equipment.model.family.name = :familyName OR :familyName IS NULL ) AND (eqd.equipment.model.name = :modelName OR :modelName IS NULL) AND (CASE WHEN eqd.endDate < :startDate THEN 1 WHEN eqd.startDate > :endDate THEN 1 ELSE 0 end)=0")
    List<EquipmentDemand> getEquipmentDemandByFamilyModelAndRange(
            @Param("familyName") String familyName,
            @Param("modelName") String modelName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
