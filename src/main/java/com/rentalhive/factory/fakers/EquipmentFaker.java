package com.rentalhive.factory.fakers;

import com.github.javafaker.Faker;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class EquipmentFaker {
    private Faker faker;

    public EquipmentFaker() {
        this.faker = new Faker();
    }
    public Equipment makeEquipment(Model model){
        return new Equipment().builder()
                .createdAt(LocalDateTime.now())
                .name(faker.name().fullName())
                .description(faker.lorem().sentence())
                .model(model)
                .price(faker.number().randomDouble(2, 0, 1000))
                .serialNumber(faker.number().digits(10))
                .build();
    }
}
