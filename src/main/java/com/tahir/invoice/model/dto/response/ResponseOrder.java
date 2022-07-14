package com.tahir.invoice.model.dto.response;

import lombok.Data;

@Data
public class ResponseOrder {
    private String status;
    private Long invoiceNumber;

}
