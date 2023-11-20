package com.rentalhive.services.demand;

import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;

public interface DemandService {
    public DemandResponseDTO saveDemand(DemandRequestDTO demandRequestDTO);
}
