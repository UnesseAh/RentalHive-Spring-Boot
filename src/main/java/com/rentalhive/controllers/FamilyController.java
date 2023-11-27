package com.rentalhive.controllers;

import com.rentalhive.controllers.vm.FamilyRequestVM;
import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Family;
import com.rentalhive.services.family.FamilyService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/families")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @PostMapping
    public ResponseEntity createFamily(@Valid @RequestBody FamilyRequestVM familyRequestVM){
        Family family = familyService.createFamily(familyRequestVM.toFamily());
        return ResponseMessage.created(family,"Family created successfully");
    }
    @GetMapping
    public ResponseEntity getAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if ( families == null ){
            return ResponseMessage.notFound("No families found");
        }
        return ResponseMessage.ok(families,"Families retrieved successfully");
    }
    @GetMapping("/{familyId}")
    public ResponseEntity getFamilyById(@PathVariable Long familyId){
        return ResponseMessage.ok(familyService.getFamilyById(familyId),"Family retrieved successfully");
    }
    @PutMapping("/{familyId}")
    public ResponseEntity updateFamily(@PathVariable Long familyId,@Valid @RequestBody FamilyRequestVM familyRequestVM){
        Family family = familyService.updateFamily(familyId,familyRequestVM.toFamily());
        return ResponseMessage.ok(family,"Family updated successfully");
    }
    @DeleteMapping("/{familyId}")
    public ResponseEntity deleteFamily(@Valid @PathVariable Long familyId){
        familyService.deleteFamily(familyId);
        return ResponseMessage.ok(null,"Family deleted successfully");

    }
    @GetMapping("/name/{familyName}")
    public ResponseEntity getFamilyByName(@Valid  @PathVariable String familyName){
        Family family = familyService.getFamilyByName(familyName);
        if ( family == null ){
            return ResponseMessage.notFound("No family found");
        }
        return ResponseMessage.ok(family,"Family retrieved successfully");
    }


}
