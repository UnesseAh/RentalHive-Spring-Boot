package com.rentalhive.repositories;

import com.rentalhive.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
