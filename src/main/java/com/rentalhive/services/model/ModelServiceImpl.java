package com.rentalhive.services.model;

import com.rentalhive.handlers.exceptionHandler.MainExceptionHandler;
import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
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
        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Long modelId, Model modelDetails) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(()-> new ResourceNotFoundException("Model not found for id : (" + modelId + ")"));
        model.setName(modelDetails.getName());
        model.setFamily(modelDetails.getFamily());
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(Long id) throws ResourceNotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Model not found for id : (" + id + ")"));
        modelRepository.delete(model);
    }

    @Override
    public Model getModelById(Long id) throws ResourceNotFoundException {
        Model model = modelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Model not found for id : (" + id + ")"));
        return model;
    }

    @Override
    public List<Model> gelAllModels() {
        return modelRepository.findAll();
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
