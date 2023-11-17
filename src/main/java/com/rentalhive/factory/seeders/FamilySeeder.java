package com.rentalhive.factory.seeders;

import com.rentalhive.factory.fakers.FamilyFaker;
import com.rentalhive.repositories.FamilyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FamilySeeder {
   FamilyRepository familyRepository;
   FamilyFaker familyFaker;
   public FamilySeeder(FamilyRepository familyRepository, FamilyFaker familyFaker){
      this.familyRepository = familyRepository;
        this.familyFaker = familyFaker;
   }
   public void seed() {
       List<String> names =  List.of("ForkLift","Loader","ScissorLift","BoomLift","Telehandler","Excavator","SkidSteer","Backhoe","Bulldozer","Compactor","Crane","DumpTruck","MotorGrader","Paver","Roller","Sweeper","Tractor","Truck","WheelLoader","Generator","AirCompressor","LightTower","Trencher","Attachments","Other");
        names.forEach(name -> {
            familyRepository.save(familyFaker.makeFamily(name));
        });
   }
}
