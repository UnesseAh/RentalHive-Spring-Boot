package com.rentalhive.services.equipement;

import com.rentalhive.models.dto.EquipmentResponseDTO;
import com.rentalhive.models.dto.EquipmentSearchDTO;

import java.util.List;

public interface EquipmentService {
     List<EquipmentResponseDTO>  getEquipements(EquipmentSearchDTO equipmentSearchDTO);

}
