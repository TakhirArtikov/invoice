package com.tahir.invoice.model.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderDetailResponse {
    private Long id;
    private LocalDate date;
    private Integer quantity;
    private String productName;
    private String customerName;
    private Integer amount;
}
