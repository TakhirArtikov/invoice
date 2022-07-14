package com.tahir.invoice.repository;

import com.tahir.invoice.model.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail,Long> {

    Optional<Detail> findByOrdId(Long orderId);
}
