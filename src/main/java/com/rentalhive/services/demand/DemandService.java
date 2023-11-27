package com.rentalhive.services.demand;


import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.enums.DemandStatus;

import java.util.List;

public interface DemandService {
    Demand createDemand(Demand demand);
    Demand getDemandById(Long id);
    List<Demand> getAllDemands();
    Demand addEquipmentToDemand(Long demandId, EquipmentDemand equipmentDemand);
    Demand updateEquipmentInDemand(Long demandId, EquipmentDemand equipmentDemand);
    Demand deleteEquipmentFromDemand(Long demandId, Long equipmentDemandId);
    void deleteDemand(Long id);
    Demand validateDemand(Long demandId);
    boolean isDemandValid(Long demandId);
    Demand changeDemandStatus(Long demandId, DemandStatus status);
}
