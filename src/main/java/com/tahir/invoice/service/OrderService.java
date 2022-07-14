package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.OrderDto;
import com.tahir.invoice.model.dto.request.CreateOrder;
import com.tahir.invoice.model.dto.response.OrderDetailResponse;
import com.tahir.invoice.model.dto.response.ResponseOrder;

import java.util.List;

public interface OrderService {
    OrderDetailResponse getOrderDetails(Long orderId);

    ResponseOrder createOrder(CreateOrder dto);

    List<OrderDto> ordersWithoutDetails();
}
