package com.tahir.invoice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordId", insertable = false, updatable = false)
    private Order order;

    private Long ordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prId", insertable = false, updatable = false)
    private Product product;

    private Long prId;
    private int quantity;
}
