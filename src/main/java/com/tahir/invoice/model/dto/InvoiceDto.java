package com.tahir.invoice.model.dto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class InvoiceDto {
    private Long id;
    private Long ordId;
    private int amount;
    private LocalDate issued;
    private LocalDate due;
}
