package com.rentalhive.factory.fakers;

import com.github.javafaker.Faker;
import com.rentalhive.models.entities.Family;
import com.rentalhive.models.entities.Model;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ModelFaker {
    private Faker faker;

    public ModelFaker() {
        this.faker = new Faker();
    }
    public Model makeModel(Family family, String modelName){
        return new Model().builder()
                .createdAt(LocalDateTime.now())
                .name(modelName)
                .family(family)
                .build();
    }
}
