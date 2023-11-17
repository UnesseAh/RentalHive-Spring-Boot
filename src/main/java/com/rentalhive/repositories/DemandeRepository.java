package com.rentalhive.repositories;

import com.rentalhive.models.entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demand , Long> {
}
