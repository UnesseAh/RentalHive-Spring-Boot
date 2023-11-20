package com.rentalhive.repositories;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);
    Equipment findBySerialNumber(String name);
    List<Equipment> findByModel(Model model);
    List<Equipment> findByPriceBetween(Double min, Double max);
    @Query("SELECT eq FROM Equipment eq WHERE (eq.model.family.name = :familyName OR :familyName IS NULL ) AND (eq.model.name = :modelName OR :modelName IS NULL)")
    List<Equipment> getEquipmentByFamilyNameAndModelName(
            @Param("familyName") String familyName,
            @Param("modelName") String modelName
    );
}
