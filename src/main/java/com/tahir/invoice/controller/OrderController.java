package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.OrderDto;
import com.tahir.invoice.model.dto.request.CreateOrder;
import com.tahir.invoice.model.dto.response.OrderDetailResponse;
import com.tahir.invoice.model.dto.response.ResponseOrder;
import com.tahir.invoice.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImpl orderService;


    @Operation(summary = "Order details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDetailResponse.class))))
    })
    @GetMapping("/details")
    private OrderDetailResponse orderDetail(@RequestParam(value = "order_id") Long orderId){
        return orderService.getOrderDetails(orderId);
    }

    @Operation(summary = "Making new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseOrder.class))))
    })
    @PostMapping("/order")
    private ResponseOrder makeOrder(@RequestBody CreateOrder order){
        return orderService.createOrder(order);
    }


    @Operation(summary = "Orders without details and placed before 6/9/2016")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))))
    })
    @GetMapping("/orders-without-details")
    private List<OrderDto> orderWithoutDetails(){
        return orderService.ordersWithoutDetails();
    }
}
