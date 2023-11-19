package com.rentalhive.repositories;

import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByName(String name);
    Equipment findBySerialNumber(String name);
    List<Equipment> findByModel(Model model);
    List<Equipment> findByPriceBetween(Double min, Double max);
}
