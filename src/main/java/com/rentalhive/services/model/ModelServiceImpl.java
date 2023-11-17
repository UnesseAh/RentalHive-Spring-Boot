package com.rentalhive.services.model;

import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelServiceImpl implements ModelService{
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model createModel(Model model) {
        return null;
    }

    @Override
    public Model updateModel(Model model) {
        return null;
    }

    @Override
    public void deleteModel(Long id) {

    }

    @Override
    public Model getModelById(Long id) {
        return null;
    }

    @Override
    public List<Model> gelAllModels() {
        return null;
    }

    @Override
    public List<Model> searchModelsByName(String name) {
        return null;
    }

    @Override
    public List<Model> searchModelsByFamily(Family family) {
        return null;
    }
}
