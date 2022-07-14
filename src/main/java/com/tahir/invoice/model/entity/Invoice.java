package com.tahir.invoice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordId", insertable = false, updatable = false,nullable = false)
    private Order order;

    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;

    private Long ordId;
    private int amount;
    private LocalDate issued;
    private LocalDate due;
}
