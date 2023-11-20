package com.rentalhive.controllers;

import com.rentalhive.models.entities.Family;
import com.rentalhive.services.family.FamilyService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @PostMapping
    public Family createFamily(@RequestBody  Family family){
        return familyService.createFamily(family);
    }

    @GetMapping
    public List<Family> getAllFamilies(){
        return familyService.gelAllFamilies();
    }

    @GetMapping("/{id}")
    public Family getFamilyById(@PathVariable(value = "id") Long id){
        return familyService.getFamilyById(id);
    }

    @PutMapping("/{id}")
    public Family updateFamily(@PathVariable(value = "id") Long id, @RequestBody Family family){
        return familyService.updateFamily(id, family);
    }

    @DeleteMapping("/{id}")
    public void deleteFamily(@PathVariable(value = "id") Long id){
        familyService.deleteFamily(id);
    }

    // TODO: map request to string
//    @PostMapping("/search")
//    public Family findFamilyByName(String name){
//        return familyService.searchFamilyByName(name);
//    }
}
