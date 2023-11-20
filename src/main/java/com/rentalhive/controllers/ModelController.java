package com.rentalhive.controllers;

import com.rentalhive.models.entities.Model;
import com.rentalhive.services.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public Model createModel(@RequestBody Model model){
         return modelService.createModel(model);
    }

    @GetMapping
    public List<Model> getAllModels(){
        return modelService.gelAllModels();
    }

    @GetMapping("{id}")
    public Model getOneModel(@PathVariable(value = "id") Long id){
        return modelService.getModelById(id);
    }

    @PutMapping("/{id}")
    public Model updateModel(@PathVariable(value = "id") Long id, @RequestBody Model model){
        return modelService.updateModel(id, model);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable(value = "id") Long id){
        modelService.deleteModel(id);
    }

}
