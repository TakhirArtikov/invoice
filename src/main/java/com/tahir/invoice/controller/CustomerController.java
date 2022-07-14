package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.CustomerDto;
import com.tahir.invoice.model.dto.PaymentDto;
import com.tahir.invoice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Operation(summary = "Customers without orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class))))
    })
    @GetMapping("/customers-without-orders")
    private List<CustomerDto> customersWithoutOrders(){
        return customerService.customersWithoutOrders();
    }

}
