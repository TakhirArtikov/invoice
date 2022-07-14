package com.tahir.invoice.model.dto.response;

import com.tahir.invoice.model.dto.PaymentDto;
import lombok.Data;

@Data
public class PaymentDetailResponse {
    private String status;
    PaymentDto paymentDto;
}
