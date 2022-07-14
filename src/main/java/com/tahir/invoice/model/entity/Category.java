package com.tahir.invoice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
