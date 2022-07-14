package com.tahir.invoice.model.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    private String name;
    private String country;
    private String address;
    private String phone;
}
