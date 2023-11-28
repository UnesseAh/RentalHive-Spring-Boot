package com.rentalhive.factory.fakers;

import com.github.javafaker.Faker;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.Model;
import com.rentalhive.models.enums.EquipmentCondition;
import com.rentalhive.models.enums.EquipmentFuel;
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
                .fuel(faker.options().option(EquipmentFuel.DIESEL,EquipmentFuel.GASOLINE,EquipmentFuel.ELECTRIC))
                .vehicleCondition(faker.options().option(EquipmentCondition.NEW,EquipmentCondition.USED))
                .color(faker.color().name())
                .price(faker.number().randomDouble(2, 0, 1000))
                .serialNumber(faker.number().digits(10))
                .build();
    }
}
