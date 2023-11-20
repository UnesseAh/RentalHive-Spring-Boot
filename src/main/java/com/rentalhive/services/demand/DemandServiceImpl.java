package com.rentalhive.services.demand;

import com.rentalhive.models.dto.DemandDTO;
import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.User;
import com.rentalhive.repositories.DemandRepository;

public class DemandServiceImpl implements DemandService {
    DemandRepository demandRepository;
    @Override
    public UserDTO saveDemand(DemandDTO demandDTO) {
        Demand demand = demandRepository.save(demandDTO.to);
        return UserDTO.fromUser(user);
    }
}
