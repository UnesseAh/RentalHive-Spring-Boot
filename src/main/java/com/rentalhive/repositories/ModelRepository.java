package com.rentalhive.repositories;

import com.rentalhive.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByFamilyName(String familyName);
    Model findModelByNameAndFamilyName(String name, String familyName);
    Model findModelByName(String name);

}
