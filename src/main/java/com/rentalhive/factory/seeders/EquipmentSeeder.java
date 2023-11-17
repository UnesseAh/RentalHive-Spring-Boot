package com.rentalhive.factory.seeders;

import com.rentalhive.factory.fakers.EquipmentFaker;
import com.rentalhive.models.entities.Model;
import com.rentalhive.repositories.EquipmentRepository;
import com.rentalhive.repositories.ModelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentSeeder {
    EquipmentRepository equipmentRepository;
    ModelRepository modelRepository;
    EquipmentFaker equipmentFaker;
    public EquipmentSeeder(EquipmentRepository equipmentRepository, EquipmentFaker equipmentFaker, ModelRepository modelRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentFaker = equipmentFaker;
        this.modelRepository = modelRepository;
    }
    public void seed(int count){
        modelRepository.findAll().forEach(model -> {
            for (int i = 0; i < count; i++) {
                equipmentRepository.save(equipmentFaker.makeEquipment(model));
            }
        });
    }
}
