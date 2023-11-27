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
    public ResponseEntity<ResponseMessage> createFamily(
            @Valid
            @RequestBody
            FamilyDTO familyDTO)
    {
        FamilyDTO createdFamily = familyService.createFamily(familyDTO);
        return new ResponseEntity<>(new ResponseMessage(
                HttpStatus.CREATED.value(),
                createdFamily,
                HttpStatus.CREATED.toString()),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllFamilies(){
        List<FamilyDTO> families = familyService.gelAllFamilies();

        if(families.isEmpty()){
            return new ResponseEntity<>(new ResponseMessage(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.toString()),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage(
                HttpStatus.FOUND.value(),
                families,
                HttpStatus.FOUND.toString()),
                HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getFamilyById(
            @PathVariable(value = "id") Long id)
    {
        Optional<Family> foundFamily = familyService.getFamilyById(id);
        if (foundFamily.isEmpty()){
            return new ResponseEntity<>(new ResponseMessage(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.toString()),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage(
                HttpStatus.FOUND.value(),
                foundFamily,
                HttpStatus.FOUND.toString()),
                HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateFamily(
            @PathVariable(value = "id") Long id,
            @RequestBody FamilyDTO familyDTO)
    {
        FamilyDTO updatedFamily = familyService.updateFamily(id, familyDTO);
        return new ResponseEntity<>(new ResponseMessage(
                HttpStatus.CREATED.value(),
                updatedFamily,
                HttpStatus.CREATED.toString()),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteFamily(@PathVariable(value = "id") Long id){
        familyService.deleteFamily(id);
        return new ResponseEntity<>(new ResponseMessage(
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT.toString()),
                HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    public Optional<Family> findFamilyByName(@RequestBody FamilyDTO familyDTO){
        Optional<Family> foundResult = familyService.searchFamilyByName(familyDTO);
         return foundResult;
    }
}
