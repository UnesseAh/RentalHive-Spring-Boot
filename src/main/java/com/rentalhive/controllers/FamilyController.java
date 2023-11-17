package com.rentalhive.controllers;

import com.rentalhive.models.entities.Family;
import com.rentalhive.services.family.FamilyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
public class FamilyController {
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/")
    public List<Family> getAllFamilies(){
        return familyService.gelAllFamilies();
    }

    @PostMapping("/")
    public Family createFamily(Family family){
        return familyService.createFamily(family);
    }

    @PutMapping("/{id}")
    public Family updateFamily(@PathVariable(value = "id") Long id, @RequestBody Family family){
        return familyService.updateFamily(id, family);
    }

    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable(value = "id") Long id){
        familyService.deleteFamily(id);
    }
}
