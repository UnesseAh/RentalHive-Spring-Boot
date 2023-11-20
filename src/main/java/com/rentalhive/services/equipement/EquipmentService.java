package com.rentalhive.services.equipement;

import com.rentalhive.models.dto.CountEquipmentDemandDTO;
import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;
import com.rentalhive.models.entities.Equipment;

import java.util.List;

public interface EquipmentService {
     List<EquipmentResponseDTO>  getEquipements(EquipmentSearchDTO equipmentSearchDTO);

}
