package com.rentalhive.repositories;

import com.rentalhive.models.entities.Invoice;
import com.rentalhive.models.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {
}
