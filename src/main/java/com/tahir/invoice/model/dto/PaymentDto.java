package com.tahir.invoice.model.dto;

import com.tahir.invoice.model.entity.Invoice;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
public class PaymentDto {

    Long id;
    Timestamp timestamp;
    int amount;
    Long invId;
}
