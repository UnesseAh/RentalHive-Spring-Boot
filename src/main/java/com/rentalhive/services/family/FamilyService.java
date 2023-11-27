package com.rentalhive.services.family;

import com.rentalhive.models.dto.FamilyDTO;
import com.rentalhive.models.entities.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyService {
    FamilyDTO createFamily(FamilyDTO familyDTO);
    FamilyDTO updateFamily(Long id, FamilyDTO newFamilyDTO);
    void deleteFamily(Long id);
    Optional<Family> getFamilyById(Long id);
    List<FamilyDTO> gelAllFamilies();
    Optional<Family> searchFamilyByName(FamilyDTO familyDTO);
}
