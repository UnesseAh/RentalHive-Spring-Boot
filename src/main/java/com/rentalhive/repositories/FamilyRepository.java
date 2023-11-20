package com.rentalhive.repositories;

import com.rentalhive.models.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findFamilyByName(String name);
}
