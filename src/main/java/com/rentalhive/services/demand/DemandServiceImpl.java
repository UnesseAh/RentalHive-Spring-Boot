package com.rentalhive.services.demand;


import com.rentalhive.models.dto.DemandDTO;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.repositories.DemandRepository;

public class DemandServiceImpl implements DemandService {
    DemandRepository demandRepository;
    @Override
    public DemandDTO saveDemand(DemandDTO demandDTO) {
        Demand demand = demandRepository.save(demandDTO.toDemand());
        return DemandDTO.fromDemand(demand);
    }
}
