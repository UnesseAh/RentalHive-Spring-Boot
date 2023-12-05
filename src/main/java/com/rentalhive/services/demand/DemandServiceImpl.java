package com.rentalhive.services.demand;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.dto.*;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.enums.Status;
import com.rentalhive.repositories.DemandRepository;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional()
public class DemandServiceImpl implements DemandService {
    DemandRepository demandRepository;
    EquipmentRepository equipmentRepository;
    EquipmentDemandRepository equipmentDemandRepository;


    public DemandServiceImpl(DemandRepository demandRepository, EquipmentRepository equipmentRepository, EquipmentDemandRepository equipmentDemandRepository) {
        this.demandRepository = demandRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentDemandRepository = equipmentDemandRepository;

    }
    @Override
    public DemandResponseDTO makeDemand(@Valid DemandRequestDTO demandRequestDTO){
        List<Long> equipmentIds = demandRequestDTO.equipmentDemands().stream().map(EquipmentDemandRequestDTO::equipmentId).toList();
        List<Equipment> equipmentList = equipmentRepository.findAllByIdIn(equipmentIds);
        if(equipmentList.size() == equipmentIds.size() ){
            Demand demand = demandRequestDTO.toDemand();
            List<EquipmentDemand> equipmentDemands = demandRequestDTO.equipmentDemands().stream().map(equipmentDemandRequestDTO -> equipmentDemandRequestDTO.toEquipmentDemand(demand, equipmentList.get(equipmentIds.indexOf(equipmentDemandRequestDTO.equipmentId())))).toList();
            demand.setEquipmentDemands(equipmentDemands);
            return DemandResponseDTO.fromDemand(demandRepository.save(demand));
        }else{
            throw new RuntimeException("Some Equipments not found");
        }
    }
    public List<DemandResponseDTO> getAllDemands(){
        return demandRepository.findAll().stream().map(DemandResponseDTO::fromDemand).toList();
    }
    public Boolean validateDemand(Long demandId){
        if(demandId == null){
            throw new IllegalArgumentException("Demand ID cannot be null");
        }
        Demand demand = demandRepository.findById(demandId).orElseThrow(() -> new RuntimeException("Demand not found"));
        demand.getEquipmentDemands().forEach(
                equipmentDemand -> {
                    List<EquipmentDemand> equipmentDemands = equipmentDemandRepository.getEquipmentDemandByIdAndRange(
                            Status.ACCEPTED,
                            equipmentDemand.getEquipment().getId(),
                            equipmentDemand.getStartDate(),
                            equipmentDemand.getEndDate()
                    );
                    if(equipmentDemands.size() > 0){
                        throw new RuntimeException("This Equipment "+equipmentDemand.getEquipment().getSerialNumber()+" not available for now");
                    }
                }
        );
        return true;


    }

    @Override
    public Demand createReservation(ReservationRequestDTO reservationRequestDTO) {
        Demand demand = reservationRequestDTO.toDemand();

        List<EquipmentDemand> equipmentDemands = new ArrayList<>();
        for(EquipmentReservationRequestDTO equipmentToReserve : reservationRequestDTO.equipmentReservations()){
            Equipment equipment =  equipmentRepository.findById(equipmentToReserve.equipmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("equipment not found" + equipmentToReserve.equipmentId()));
            EquipmentDemand equipmentDemand = new EquipmentDemand();
            equipmentDemand.setEquipment(equipment);
            equipmentDemand.setStartDate(equipmentToReserve.startDate());
            equipmentDemand.setEndDate(equipmentToReserve.endDate());
            equipmentDemands.add(equipmentDemand);
        }
        demand.setEquipmentDemands(equipmentDemands);

        return demandRepository.save(demand);
    }


}
