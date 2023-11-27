package com.rentalhive.services.family;

import com.rentalhive.models.dto.FamilyDTO;
import com.rentalhive.models.entities.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyService {
    FamilyDTO createFamily(FamilyDTO familyDTO);
    Family updateFamily(Long familyId, Family family);
    void deleteFamily(Long id);
    Family getFamilyById(Long id);
    List<Family> gelAllFamilies();
    Optional<Family> searchFamilyByName(FamilyDTO familyDTO);
}
