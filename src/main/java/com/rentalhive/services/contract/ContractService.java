package com.rentalhive.services.contract;

import com.rentalhive.models.entities.Contract;

public interface ContractService {
    Contract createContract(Contract contract);
    Contract getContractById(Long id);
    Contract getContractByDemandId(Long demandId);
    Contract signContract(Long contractId);


}
