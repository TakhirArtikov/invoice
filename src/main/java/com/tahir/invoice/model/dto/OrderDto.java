package com.tahir.invoice.model.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class OrderDto {
    private Long id;
    private LocalDate date;
    private Long custId;
}
