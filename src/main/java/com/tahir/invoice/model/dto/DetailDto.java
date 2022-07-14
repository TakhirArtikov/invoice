package com.tahir.invoice.model.dto;
import lombok.Data;


@Data
public class DetailDto {
    private Long id;
    private Long ordId;
    private Long prId;
    private int quantity;
}
