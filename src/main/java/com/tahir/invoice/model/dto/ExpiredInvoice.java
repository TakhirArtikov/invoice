package com.tahir.invoice.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpiredInvoice {

    private String custName;
    private int amount;
    private LocalDate issued;
    private LocalDate due;

}
