package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.PaymentDto;
import com.tahir.invoice.model.dto.request.MakePayment;
import com.tahir.invoice.model.dto.response.PaymentDetailResponse;

public interface PaymentService {
    PaymentDto getDetails(Long id);
    PaymentDetailResponse paymentDetails(Long invoiceId);
}
