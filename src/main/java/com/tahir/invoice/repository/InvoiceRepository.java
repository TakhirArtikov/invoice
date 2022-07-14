package com.tahir.invoice.repository;

import com.tahir.invoice.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {


    Optional<Invoice> findByOrdId(Long orderId);
}
