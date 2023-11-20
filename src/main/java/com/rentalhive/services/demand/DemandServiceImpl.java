package com.rentalhive.services.demand;


import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.repositories.DemandRepository;
import org.springframework.stereotype.Service;

@Service
public class DemandServiceImpl implements DemandService {
    DemandRepository demandRepository;

    public DemandServiceImpl(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    @Override
    public DemandResponseDTO saveDemand(DemandRequestDTO demandRequestDTO) {
        Demand demand = demandRepository.save(demandRequestDTO.toDemand());
        return DemandResponseDTO.fromDemand(demand);
    }
}
