package com.rentalhive.factory.seeders;

import com.rentalhive.factory.fakers.ModelFaker;
import com.rentalhive.models.entities.Family;
import com.rentalhive.repositories.FamilyRepository;
import com.rentalhive.repositories.ModelRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ModelSeeder {
    ModelRepository modelRepository;
    FamilyRepository familyRepository;
    ModelFaker modelFaker;
    public ModelSeeder(ModelRepository modelRepository, ModelFaker modelFaker, FamilyRepository familyRepository) {
        this.modelRepository = modelRepository;
        this.modelFaker = modelFaker;
        this.familyRepository = familyRepository;
    }
    public void seed(){
        List<String> modelsOfFamily = List.of("Cat","Scania","Volvo","JCB","Hitachi","Komatsu","Kobelco");
              familyRepository.findAll().forEach(family -> {
                  modelsOfFamily.forEach(modelName -> {
                      modelRepository.save(modelFaker.makeModel(family,modelName));
                  });
              }
              );
    }
}
