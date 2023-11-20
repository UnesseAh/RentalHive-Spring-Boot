package com.rentalhive.services.family;

import com.rentalhive.models.entities.Family;
import com.rentalhive.repositories.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService{

    private final FamilyRepository familyRepository;
    public FamilyServiceImpl(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Long familyId, Family newFamily) {
        Family family = familyRepository.findById(familyId).get();
        family.setName(newFamily.getName());
        return familyRepository.save(family);
    }

    @Override
    public void deleteFamily(Long id) {
        familyRepository.deleteById(id);
    }

    @Override
    public Family getFamilyById(Long id) {
        return familyRepository.findById(id).get();
    }

    @Override
    public List<Family> gelAllFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public Family searchFamilyByName(String name) {
        return familyRepository.findFamilyByName(name);
    }
}
