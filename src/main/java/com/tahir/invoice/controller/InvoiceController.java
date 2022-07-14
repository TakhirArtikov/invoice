package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.ExpiredInvoice;
import com.tahir.invoice.model.dto.WrongDateInvoice;
import com.tahir.invoice.service.InvoiceService;
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
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Operation(summary = "Expired invoices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExpiredInvoice.class))))
    })
    @GetMapping("/expired-invoices")
    private List<ExpiredInvoice> expiredInvoice(){
        return invoiceService.expiredInvoiceList();
    }

    @Operation(summary = "Wrong date invoice list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WrongDateInvoice.class))))
    })
    @GetMapping("/wrong-date-invoices")
    private List<WrongDateInvoice> wrongDateInvoice(){
        return invoiceService.wrongDateInvoiceList();
    }
}
