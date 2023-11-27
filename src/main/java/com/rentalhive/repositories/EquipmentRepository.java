package com.rentalhive.repositories;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByModelName(String modelName);
    List<Equipment> findAllByModelFamilyName(String familyName);

    List<Equipment> findAllByIdIn( List<Long> ids);


    @Query("SELECT eq FROM Equipment eq WHERE (eq.model.family.name = :familyName OR :familyName IS NULL ) AND (eq.model.name = :modelName OR :modelName IS NULL)")
    List<Equipment> getEquipmentByFamilyNameAndModelName(
            @Param("familyName") String familyName,
            @Param("modelName") String modelName
    );
    Equipment findEquipmentBySerialNumber(String serialNumber);
}
