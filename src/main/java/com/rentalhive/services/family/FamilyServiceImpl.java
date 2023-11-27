package com.rentalhive.services.family;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.dto.FamilyDTO;
import com.rentalhive.models.entities.Family;
import com.rentalhive.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService{
    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        Optional<Family> foundFamily = Optional.ofNullable(familyRepository.findByName(familyDTO.name()));
        if(foundFamily.isPresent()){
            throw new EntityExistsException("Family already exists");
        }
        Family family = familyRepository.save(familyDTO.toFamily());
        return FamilyDTO.toFamilyDTO(family);
    }

    @Override
    public FamilyDTO updateFamily(Long id, FamilyDTO newFamilyDTO) {
        Optional<Family> foundFamily = familyRepository.findById(id);
        if(foundFamily.isEmpty()){
            throw new ResourceNotFoundException("Family with id " + id + " doesn't exist");
        }

        Optional<Family> foundFamilyByName = Optional.ofNullable(familyRepository.findByName(newFamilyDTO.name()));
        if(foundFamilyByName.isPresent()){
            throw new EntityExistsException("Family with this name already exists");
        }

        foundFamily.get().setName(newFamilyDTO.name());

        Family savedFamily = familyRepository.save(foundFamily.get());
        return FamilyDTO.toFamilyDTO(savedFamily);
    }

    @Override
    public void deleteFamily(Long id) {
        Optional<Family> foundFamily = getFamilyById(id);
        if (foundFamily.isEmpty()){
            throw new ResourceNotFoundException("Family with id " + id + " doesn't exist");
        }
        familyRepository.deleteById(id);
    }

    @Override
    public Optional<Family> getFamilyById(Long id) {
        Optional<Family> foundFamily = familyRepository.findById(id);
        return foundFamily;
    }

    @Override
    public List<FamilyDTO> gelAllFamilies() {
        List<FamilyDTO> families = new ArrayList<>();
        familyRepository.findAll().forEach(family -> families.add(FamilyDTO.toFamilyDTO(family)));
        return families;
    }

    @Override
    public Optional<Family> searchFamilyByName(FamilyDTO familyDTO) {
        String familyName = familyDTO.name();
        return Optional.ofNullable(familyRepository.findByName(familyName));
    }
}
