package com.tahir.invoice.model.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Integer price;
}
