package com.tahir.invoice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Timestamp timestamp;
    int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invId", insertable = false, updatable = false)
    Invoice invoice;

    Long invId;
}
