package com.rentalhive.services.demand;

import com.rentalhive.models.dto.DemandRequestDTO;
import com.rentalhive.models.dto.DemandResponseDTO;
import com.rentalhive.models.dto.ReservationRequestDTO;
import com.rentalhive.models.entities.Demand;

import java.util.List;

public interface DemandService {
    public DemandResponseDTO makeDemand(DemandRequestDTO demandRequestDTO);
    public List<DemandResponseDTO> getAllDemands();
    public Boolean validateDemand(Long demandId);
    Demand createReservation(ReservationRequestDTO reservationRequestDTO);
}
