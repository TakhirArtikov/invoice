package com.tahir.invoice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Detail> details;

    private Long categoryId;

    private String description;
    private Integer price;
}
