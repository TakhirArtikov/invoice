package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.CustomerDto;


import java.util.List;

public interface CustomerService {
    List<CustomerDto> customersWithoutOrders();
}
