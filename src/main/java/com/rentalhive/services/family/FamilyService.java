package com.rentalhive.services.family;

import com.rentalhive.models.entities.Family;

import java.util.List;

public interface FamilyService {
    Family createFamily(Family family);
    Family updateFamily(Family family);
    void deleteFamily(Long id);
    Family getFamilyById(Long id);
    List<Family> gelAllFamilies();
    List<Family> searchFamilyByName(String name);
}
