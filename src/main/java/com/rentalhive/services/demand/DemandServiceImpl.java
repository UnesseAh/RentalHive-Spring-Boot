package com.rentalhive.services.demand;

import com.rentalhive.handlers.exceptionHandler.OperationException;
import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.enums.DemandStatus;
import com.rentalhive.repositories.DemandRepository;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.services.equipment.EquipmentService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class DemandServiceImpl implements DemandService {
    private final DemandRepository demandRepository;
    private final EquipmentService equipmentService;
    private final EquipmentDemandRepository equipmentDemandRepository;
    public DemandServiceImpl(DemandRepository demandRepository, EquipmentService equipmentService, EquipmentDemandRepository equipmentDemandRepository) {
        this.demandRepository = demandRepository;
        this.equipmentService = equipmentService;
        this.equipmentDemandRepository = equipmentDemandRepository;
    }
    @Override
    public Demand createDemand(Demand demand) {
        List<EquipmentDemand> equipmentDemands = demand.getEquipmentDemands();
        List<Long> equipmentIds = equipmentDemands.stream()
                .map(equipmentDemand
                        -> equipmentDemand.getEquipment().getId())
                .toList();
        equipmentIds.forEach(equipmentId -> {
            Equipment equipment = equipmentService.getEquipmentById(equipmentId);
            if(equipment == null){
                throw new ResourceNotFoundException("Equipment id: " + equipmentId + " not found");
            }
            equipmentDemands.stream()
                    .filter(equipmentDemand -> equipmentDemand.getEquipment().getId().equals(equipmentId))
                    .forEach(equipmentDemand -> {
                        equipmentDemand.setEquipment(equipment);
                        equipmentDemand.setDemand(demand);
                    });
        });
        Demand demand1 = demandRepository.save(demand);
        equipmentDemandRepository.saveAll(equipmentDemands);
        demand1.setEquipmentDemands(equipmentDemands);
        return demand1;
    }
    @Override
    public Demand getDemandById(Long id) {
        return demandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Demand id: " + id + " not found"));
    }
    @Override
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    @Override
    public void deleteDemand(Long id) {
        demandRepository.deleteById(id);
    }
    @Override
    public Demand addEquipmentToDemand(Long demandId, EquipmentDemand equipmentDemand) {
        Demand demand = getDemandById(demandId);
        Equipment equipment = equipmentService.getEquipmentById(equipmentDemand.getEquipment().getId());
         Integer eqdSize = equipmentDemandRepository.findAllByEquipment_Id(equipment.getId()).size();
        if(eqdSize > 0){
            throw new OperationException("Equipment with id: " + equipment.getId() + " is already in use");
        }
        equipmentDemand.setEquipment(equipment);
        equipmentDemand.setDemand(demand);
        equipmentDemandRepository.save(equipmentDemand);
        demand.setStatus(DemandStatus.IN_REVIEW);
        return demandRepository.save(demand);
    }
    @Override
    public Demand updateEquipmentInDemand(Long demandId, EquipmentDemand equipmentDemand) {
        EquipmentDemand equipmentDemand1 = equipmentDemandRepository.findEquipmentDemandByEquipment_IdAndDemand_Id(equipmentDemand.getEquipment().getId(), demandId);
        if(equipmentDemand1 == null){
            throw new OperationException("Equipment demand with equipment id: " + equipmentDemand.getEquipment().getId() + " and demand id: " + demandId + " not found");
        }
        equipmentDemand1.setStartDate(equipmentDemand.getStartDate());
        equipmentDemand1.setEndDate(equipmentDemand.getEndDate());
        equipmentDemandRepository.save(equipmentDemand1);
        Demand demand  = getDemandById(demandId);
        demand.setStatus(DemandStatus.IN_REVIEW);
        return demandRepository.save(demand);
    }
    @Override
    public Demand deleteEquipmentFromDemand(Long demandId, Long equipmentDemandId) {
        EquipmentDemand equipmentDemand = equipmentDemandRepository.findEquipmentDemandByEquipment_IdAndDemand_Id(equipmentDemandId, demandId);
        if(equipmentDemand == null){
            throw new OperationException("Equipment demand with equipment id: " + equipmentDemandId + " and demand id: " + demandId + " not found");
        }
        equipmentDemandRepository.delete(equipmentDemand);
        return getDemandById(demandId);
    }
    @Override
    public Demand validateDemand(Long id){
        Demand demand = getDemandById(id);
        if(demand.getStatus()==DemandStatus.IN_REVIEW) {
            List<EquipmentDemand> equipmentDemands = demand.getEquipmentDemands();
            equipmentDemands.forEach(
                    eqd -> {
                        List<EquipmentDemand> eqds = equipmentDemandRepository.getEquipmentDemandByIdAndRange(
                                DemandStatus.PENDING_CONTRACT_CREATION,
                                eqd.getEquipment().getId(),
                                eqd.getStartDate(),
                                eqd.getEndDate());
                        if ( eqds.size() > 0 ) {
                            throw new OperationException("Can't validate Demand ,Equipment with Serial Number: " + eqd.getEquipment().getSerialNumber() + " is all ready reserved on this date");
                        }
                    }
            );
            demand.setStatus(DemandStatus.PENDING_NEGOTIATION);
            return demandRepository.save(demand);
        }else{
            throw new OperationException("the Demand is All ready Validated");
        }
    }
    @Override
    public boolean isDemandValid(Long demandId){
        Demand demand = getDemandById(demandId);
        return demand.getStatus() != DemandStatus.IN_REVIEW;
    }
    @Override
    public Demand changeDemandStatus(Long demandId, DemandStatus status){
        Demand demand = getDemandById(demandId);
        demand.setStatus(status);
        return demandRepository.save(demand);
    }
}
