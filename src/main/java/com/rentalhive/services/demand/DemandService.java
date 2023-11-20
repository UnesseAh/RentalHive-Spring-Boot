package com.rentalhive.services.demand;

import com.rentalhive.models.dto.DemandDTO;
import com.rentalhive.models.dto.UserDTO;

public interface DemandService {
    public UserDTO saveDemand(DemandDTO demandDTO);
}
