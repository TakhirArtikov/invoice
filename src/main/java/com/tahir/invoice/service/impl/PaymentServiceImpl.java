package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.PaymentDto;
import com.tahir.invoice.model.dto.response.PaymentDetailResponse;
import com.tahir.invoice.model.entity.Invoice;
import com.tahir.invoice.model.entity.Payment;
import com.tahir.invoice.repository.InvoiceRepository;
import com.tahir.invoice.repository.PaymentRepository;
import com.tahir.invoice.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private InvoiceRepository invoiceRepository;

    @Override
    public PaymentDto getDetails(Long id) {
        Optional<Payment> paymentEntity = paymentRepository.findById(id);
        if (paymentEntity.isPresent()) {
            PaymentDto dto = new PaymentDto();
            BeanUtils.copyProperties(paymentEntity, dto);
            return dto;
        }
        return null;
    }

    @Override
    public PaymentDetailResponse paymentDetails(Long invoiceId) {
        PaymentDetailResponse paymentDetailResponse = new PaymentDetailResponse();

        Optional<Invoice> invoiceEntity = invoiceRepository.findById(invoiceId);

        Payment payment = new Payment();

        if (invoiceEntity.isPresent()) {

            payment.setInvId(invoiceId);
            payment.setAmount(invoiceEntity.get().getAmount());
            payment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            paymentRepository.saveAndFlush(payment);

            PaymentDto dto = new PaymentDto();
            BeanUtils.copyProperties(payment, dto);
            paymentDetailResponse.setPaymentDto(dto);
            paymentDetailResponse.setStatus("SUCCESS");
        } else {
            paymentDetailResponse.setStatus("FAILED");
            paymentDetailResponse.setPaymentDto(null);
        }

        return paymentDetailResponse;
    }


}
