package com.rentalhive.controllers.vm;

import com.rentalhive.models.entities.Demand;

import java.util.List;

public record DemandResponseVM(
        Long id,
        String title,
        String description,
        UserVM user,
        String status,
        List<EquipmentDemandResponseVM> equipmentDemands,
        List<QuoteResponseVM> quotes,
        ContractResponseVM contract
) {
    public static DemandResponseVM fromDemand(Demand demand){
        return new DemandResponseVM(
                demand.getId(),
                demand.getTitle(),
                demand.getDescription(),
                UserVM.fromUser(demand.getUser()),
                demand.getStatus().toString(),
                demand.getEquipmentDemands()!=null?demand.getEquipmentDemands().stream().map(EquipmentDemandResponseVM::fromEquipmentDemand).toList():null,
                demand.getQuotes()!=null?demand.getQuotes().stream().map(QuoteResponseVM::fromQuote).toList():null,
                demand.getContract()!=null?ContractResponseVM.fromContract(demand.getContract()):null
        );
    }
}
