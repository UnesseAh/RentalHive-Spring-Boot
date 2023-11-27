package com.rentalhive.controllers;

import com.rentalhive.controllers.vm.DemandResponseVM;
import com.rentalhive.controllers.vm.EquipmentDemandRequestVM;
import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.controllers.vm.DemandRequestVM;
import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.services.demand.DemandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demands")
public class DemandController {
    DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> createDemand(@Valid @RequestBody DemandRequestVM demandRequestDTO){
        Demand demand = demandRequestDTO.toDemand();
        List<EquipmentDemand> equipmentDemands = demandRequestDTO.equipmentDemands().stream().map(eqd -> eqd.toEquipmentDemand()).toList();
        demand.setEquipmentDemands(equipmentDemands);
        demand = demandService.createDemand(demand);
        DemandResponseVM demandResponseDTO = DemandResponseVM.fromDemand(demand);
        return ResponseMessage.created(demandResponseDTO,
                "Demand created successfully");
    }

    @GetMapping()
    public ResponseEntity getAllDemands(){
        List<Demand> demands = demandService.getAllDemands();
        if ( demands == null ){
            return ResponseMessage.notFound("No demands found");
        }
        List<DemandResponseVM> demandResponseDTOs = demands.stream().map(demand -> DemandResponseVM.fromDemand(demand)).toList();
        return ResponseMessage.ok(demandResponseDTOs,
                "Demands retrieved successfully");
    }

    @PostMapping("/{demandId}/equipment")
    public ResponseEntity<ResponseMessage> addEquipmentToDemand(@PathVariable Long demandId, @RequestBody EquipmentDemandRequestVM eqdVM){
        EquipmentDemand equipmentDemand = eqdVM.toEquipmentDemand();
        Demand demand = demandService.addEquipmentToDemand(demandId, equipmentDemand);
        DemandResponseVM demandResponseDTO = DemandResponseVM.fromDemand(demand);
        return ResponseMessage.created(demandResponseDTO,
                "Equipment Demand created successfully");
    }
    @PutMapping("/{demandId}/equipment")
    public ResponseEntity<ResponseMessage> updateEquipmentInDemand(@PathVariable Long demandId, @RequestBody EquipmentDemandRequestVM eqdVM){
        EquipmentDemand equipmentDemand = eqdVM.toEquipmentDemand();
        Demand demand = demandService.updateEquipmentInDemand(demandId, equipmentDemand);
        DemandResponseVM demandResponseDTO = DemandResponseVM.fromDemand(demand);
        return ResponseMessage.ok(demandResponseDTO,
                "Equipment Demand updated successfully");
    }
    @DeleteMapping("/{demandId}/equipment/{equipmentId}")
    public ResponseEntity<ResponseMessage> deleteEquipmentFromDemand(@PathVariable Long demandId, @PathVariable Long equipmentId){
        Demand demand = demandService.deleteEquipmentFromDemand(demandId, equipmentId);
        DemandResponseVM demandResponseVM = DemandResponseVM.fromDemand(demand);
        return ResponseMessage.ok(demandResponseVM,
                "Equipment Demand deleted successfully");
    }
    @PutMapping("/{demandId}/validate")
    public ResponseEntity validateDemand(@PathVariable Long demandId){
        Demand demand = demandService.validateDemand(demandId);
        DemandResponseVM demandResponseVM = DemandResponseVM.fromDemand(demand);
        return ResponseMessage.ok(demandResponseVM,
                "Demand has been validated successfully");

    }


}

