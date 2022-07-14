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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    List<Detail> details;

    @OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "order")
    private Invoice invoice;

    private Long custId;
}
