package com.rentalhive.services.model;

import com.rentalhive.handlers.exceptionHandler.MainExceptionHandler;
import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.ModelRepository;
import com.rentalhive.services.family.FamilyService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelServiceImpl implements ModelService{
    private final ModelRepository modelRepository;
    private final FamilyService familyService;
    public ModelServiceImpl(ModelRepository modelRepository, FamilyService familyService) {
        this.modelRepository = modelRepository;
        this.familyService = familyService;
    }
    @Override
    public Model createModel(Model model) {
        Family family = familyService.getFamilyByName(model.getFamily().getName());
        if ( family == null ){
            throw new ResourceNotFoundException("Family name: " + model.getFamily().getName() + " not found");
        }
        model.setFamily(family);
        return modelRepository.save(model);
    }
    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Model id: " + id + " not found"));
    }
    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }
    @Override
    public Model updateModel(Long id,Model model) {
        getModelById(id);
        Family family = familyService.getFamilyByName(model.getFamily().getName());
        if ( family == null ){
            throw new ResourceNotFoundException("Family name: " + model.getFamily().getName() + " not found");
        }
        model.setFamily(family);
        model.setId(id);
        return modelRepository.save(model);
    }
    @Override
    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }
    @Override
    public List<Model> getAllModelsByFamily(String familyName) {
        return modelRepository.findAllByFamilyName(familyName);
    }
    @Override
    public Model getModelByName(String name) {
        if(name == null){
            throw new ResourceNotFoundException("Model name is required");
        }
        return modelRepository.findModelByName(name);
    }
    @Override
    public Model getModelByNameAndFamilyName(String name, String familyName) {
        if(name == null || familyName == null){
            throw new ResourceNotFoundException("Model name and family name are required");
        }
        return modelRepository.findModelByNameAndFamilyName(name,familyName);
    }
}
