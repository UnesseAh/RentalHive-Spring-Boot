package com.rentalhive.factory.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {
    UserSeeder userSeeder;
    FamilySeeder familySeeder;
    ModelSeeder modelSeeder;
    EquipmentSeeder equipmentSeeder;
    @Autowired
    public MainSeeder(UserSeeder userSeeder, FamilySeeder familySeeder, ModelSeeder modelSeeder, EquipmentSeeder equipmentSeeder) {
        this.userSeeder = userSeeder;
        this.familySeeder = familySeeder;
        this.modelSeeder = modelSeeder;
        this.equipmentSeeder = equipmentSeeder;
    }
    @Override
    public void run(String... args) throws Exception {
        userSeeder.seed(5);
        familySeeder.seed();
        modelSeeder.seed();
        equipmentSeeder.seed(1);

    }

}
