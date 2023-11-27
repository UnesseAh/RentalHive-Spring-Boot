package com.rentalhive.controllers;

import com.rentalhive.controllers.vm.ModelRequestVM;
import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Model;
import com.rentalhive.services.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
public class ModelController {
    ModelService modelService;
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    @PostMapping
    public ResponseEntity createModel(@Valid @RequestBody ModelRequestVM modelRequestVM){
        return ResponseMessage.created(
                modelService.createModel(modelRequestVM.toModel()),
                "Model created successfully");
    }
    @GetMapping
    public ResponseEntity getAllModels(){
        List<Model> models = modelService.getAllModels();
        if ( models == null ){
            return ResponseMessage.notFound("No models found");
        }
        return ResponseMessage.ok(models,
               "Models retrieved successfully");
    }
    @GetMapping("/{modelId}")
    public ResponseEntity getModelById(@PathVariable Long modelId){
        return ResponseMessage.ok(modelService.getModelById(modelId),
                "Model retrieved successfully");
    }
    @PutMapping("/{modelId}")
    public ResponseEntity updateModel(@PathVariable Long modelId,@Valid @RequestBody ModelRequestVM modelRequestVM){
        return ResponseMessage.ok(modelService.updateModel(modelId,modelRequestVM.toModel()),
                "Model updated successfully");
    }
    @DeleteMapping("/{modelId}")
    public ResponseEntity deleteModel(@Valid @PathVariable Long modelId){
        modelService.deleteModel(modelId);
        return ResponseMessage.ok(null,"Model deleted successfully");
    }
    @GetMapping("/family/{familyName}")
    public ResponseEntity getAllModelsByFamily(@Valid  @PathVariable String familyName){
        List<Model> models = modelService.getAllModelsByFamily(familyName);
        if ( models == null ){
            return ResponseMessage.notFound("No models found");
        }
        return ResponseMessage.ok(modelService.getAllModelsByFamily(familyName),
                "Models retrieved successfully");
    }



}
