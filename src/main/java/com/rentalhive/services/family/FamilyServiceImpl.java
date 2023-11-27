package com.rentalhive.services.family;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
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
    public Family getFamilyById(Long id) {
        if ( id == null ){
            throw new ResourceNotFoundException("Family id is required");
        }
        return familyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Family id: " + id + " not found"));
    }
    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }
    @Override
    public Family updateFamily(Long id,Family family) {
        getFamilyById(id);
        family.setId(id);
        return  familyRepository.save(family);
    }
    @Override
    public void deleteFamily(Long id) {
        getFamilyById(id);
        familyRepository.deleteById(id);
    }
    @Override
    public Family getFamilyByName(String name) {
        if ( name == null ){
            throw new ResourceNotFoundException("Family name is  required");
        }
        return familyRepository.findFamilyByName(name);
    }

}
