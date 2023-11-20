package com.rentalhive.controllers;

import com.rentalhive.models.entities.Family;
import com.rentalhive.services.family.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
public class FamilyController {
    @Autowired
    private FamilyService familyService;


    @GetMapping
    public List<Family> getAllFamilies(){
        return familyService.gelAllFamilies();
    }

    @PostMapping
    public Family createFamily(@RequestBody  Family family){
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
