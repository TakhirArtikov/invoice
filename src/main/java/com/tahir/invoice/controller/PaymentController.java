package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.PaymentDto;
import com.tahir.invoice.model.dto.ProductDto;
import com.tahir.invoice.model.dto.response.PaymentDetailResponse;
import com.tahir.invoice.service.impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentServiceImpl paymentService;

    @Operation(summary = "Payment details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PaymentDto.class))))
    })
    @GetMapping("/details")
    private PaymentDto getPaymentDetailsById(@RequestParam(value = "payment_details_id") Long id){
        return paymentService.getDetails(id);
    }

    @Operation(summary = "making payment details by invoice id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PaymentDetailResponse.class))))
    })
    @PostMapping("/make-payment")
    private PaymentDetailResponse makePayment(@RequestParam(value = "invoce_id")Long invoiceId){
        return paymentService.paymentDetails(invoiceId);
    }

}
