package com.rentalhive.services.family;

import com.rentalhive.models.dto.FamilyDTO;
import com.rentalhive.models.entities.Family;
import com.rentalhive.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService{
    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        Optional<Family> foundFamily = Optional.ofNullable(familyRepository.findByName(familyDTO.name()));
        if(!foundFamily.isPresent()){
            throw new EntityExistsException("Family already exists");
        }
        Family family = familyRepository.save(familyDTO.toFamily());
        return FamilyDTO.toFamilyDTO(family);
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
    public Optional<Family> searchFamilyByName(FamilyDTO familyDTO) {
        String familyName = familyDTO.name();
        return Optional.ofNullable(familyRepository.findByName(familyName));
    }
}
