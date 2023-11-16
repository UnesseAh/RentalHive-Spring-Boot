package com.rentalhive.factory.fakers;

import com.github.javafaker.Faker;
import com.rentalhive.models.entities.Family;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class FamilyFaker {
    private Faker faker;

    public FamilyFaker() {
        this.faker = new Faker();
    }
    public Family makeFamily(String name){
        return new Family().builder()
                .createdAt(LocalDateTime.now())
                .name(name)
                .build();
    }

}
