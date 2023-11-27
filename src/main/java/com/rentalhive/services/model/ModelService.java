package com.rentalhive.services.model;

import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ModelService {
    Model createModel(Model model);
    Model getModelById(Long id);
    List<Model> getAllModels();
    Model updateModel(Long id,Model model);
    void deleteModel(Long id);
    List<Model> getAllModelsByFamily(String familyName);
    Model getModelByName(String name);
    Model getModelByNameAndFamilyName(String name, String familyName);

}
