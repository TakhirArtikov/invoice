package com.tahir.invoice.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WrongDateInvoice {
    private Long invoiceId;
    private Long orderId;
    private LocalDate issuedDate;
    private LocalDate orderDate;
}
