package com.rentalhive.services.model;

import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;

import java.util.List;

public interface ModelService {
    Model createModel(Model model);
    Model updateModel(Long modelId, Model model);
    void deleteModel(Long id);
    Model getModelById(Long id);
    List<Model> gelAllModels();
    List<Model> searchModelsByName(String name);
    List<Model> searchModelsByFamily(Family family);
}
