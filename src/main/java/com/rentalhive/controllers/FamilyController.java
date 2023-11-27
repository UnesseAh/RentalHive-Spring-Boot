package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.FamilyDTO;
import com.rentalhive.models.entities.Family;
import com.rentalhive.services.family.FamilyService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/families")
public class FamilyController {
    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createFamily(@Valid @RequestBody FamilyDTO familyDTO){
        FamilyDTO result = familyService.createFamily(familyDTO);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(),result,HttpStatus.OK.toString()),HttpStatus.OK);

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

    @PostMapping("/search")
    public Optional<Family> findFamilyByName(@RequestBody FamilyDTO familyDTO){
        Optional<Family> foundResult = familyService.searchFamilyByName(familyDTO);
         return foundResult;
    }
}
