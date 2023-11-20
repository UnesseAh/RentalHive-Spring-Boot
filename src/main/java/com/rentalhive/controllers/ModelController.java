package com.rentalhive.controllers;

import com.rentalhive.models.entities.Model;
import com.rentalhive.services.model.ModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/")
    public List<Model> getAllModels(){
        return modelService.gelAllModels();
    }

    @PostMapping("/")
    public Model createModel(@RequestBody Model model){
        return modelService.createModel(model);
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
