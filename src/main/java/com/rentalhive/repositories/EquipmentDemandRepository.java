package com.rentalhive.repositories;

import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.enums.DemandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentDemandRepository  extends JpaRepository<EquipmentDemand, Long> {

    @Query("SELECT eqd FROM EquipmentDemand eqd WHERE  eqd.equipment.id = :id  AND eqd.demand.status=:status AND (CASE WHEN eqd.endDate < :startDate THEN 1 WHEN eqd.startDate > :endDate THEN 1 ELSE 0 end)=0")
    List<EquipmentDemand> getEquipmentDemandByIdAndRange(
            @Param("status") DemandStatus status,
            @Param("id") Long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    EquipmentDemand findEquipmentDemandByEquipment_IdAndDemand_Id(Long equipmentId, Long demandId);
    List<EquipmentDemand> findAllByEquipment_Id(Long id);

}
