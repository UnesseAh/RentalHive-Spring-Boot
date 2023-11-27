package com.rentalhive.services.family;

import com.rentalhive.models.entities.Family;

import java.util.List;

public interface FamilyService {
    Family createFamily(Family family);
    Family getFamilyById(Long id);
    List<Family> getAllFamilies();
    Family updateFamily(Long id,Family family);
    void deleteFamily(Long id);
    Family getFamilyByName(String name);

}
