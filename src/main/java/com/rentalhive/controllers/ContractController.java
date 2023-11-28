package com.rentalhive.controllers;

import com.rentalhive.controllers.vm.ContractRequestVM;
import com.rentalhive.controllers.vm.ContractResponseVM;
import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.entities.Contract;
import com.rentalhive.services.contract.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {
    ContractService contractService;
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }
    @GetMapping("/demand/{demandId}")
    public ResponseEntity getContractByDemandId(@PathVariable Long demandId){
        Contract contract = contractService.getContractByDemandId(demandId);
        return ResponseMessage.ok(ContractResponseVM.fromContract(contract),
                "Contract retrieved successfully");
    }
    @GetMapping("/{contractId}")
    public ResponseEntity getContractById(@PathVariable Long contractId){
        Contract contract = contractService.getContractById(contractId);
        return ResponseMessage.ok(ContractResponseVM.fromContract(contract),
                "Contract retrieved successfully");
    }
    @PostMapping()
    public ResponseEntity<ResponseMessage> createContract(@RequestBody ContractRequestVM contractRequestVM){
        Contract contract = contractService.createContract(contractRequestVM.toContract());
        return ResponseMessage.created(ContractResponseVM.fromContract(contract),
                "Contract created successfully");
    }
    @PutMapping("/{contractId}")
    public ResponseEntity<ResponseMessage> signContract(@PathVariable Long contractId){
        Contract contract = contractService.signContract(contractId);
        return ResponseMessage.ok(ContractResponseVM.fromContract(contract),
                "Contract signed successfully");
    }


}
