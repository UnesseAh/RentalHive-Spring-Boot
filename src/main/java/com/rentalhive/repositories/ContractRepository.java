package com.rentalhive.repositories;

import com.rentalhive.models.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
    Contract findContractByDemandId(Long demandId);
    @Modifying(clearAutomatically = true)
    Contract save(Contract contract);
}
