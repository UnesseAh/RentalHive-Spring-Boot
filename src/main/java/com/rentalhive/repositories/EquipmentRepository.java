package com.rentalhive.repositories;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
//    get all equipment by model family name and model name
    @Query("SELECT eq FROM Equipment eq WHERE (eq.model.family.name = :familyName OR :familyName IS NULL ) AND (eq.model.name = :modelName OR :modelName IS NULL)")
    List<Equipment> getEquipmentByFamilyNameAndModelName(
            @Param("familyName") String familyName,
            @Param("modelName") String modelName
    );


}
