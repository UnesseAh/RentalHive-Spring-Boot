package com.rentalhive.services.demand;

import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;

import java.util.List;

public interface DemandService {
    public DemandResponseDTO makeDemand(DemandRequestDTO demandRequestDTO);
    public List<DemandResponseDTO> getAllDemands();
    public Boolean validateDemand(Long demandId);
}
