package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.CategoryDto;
import com.tahir.invoice.model.dto.ProductDto;
import com.tahir.invoice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Operation(summary = "Product List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))))
    })
    @GetMapping("/list")
    private List<ProductDto> getList(){
        return productService.getProductList();
    }

    @Operation(summary = "Product details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))))
    })
    @GetMapping("/details")
    private ProductDto getProductById(@RequestParam(value = "product_id") Long productId){
        return productService.getProductDetailsById(productId);
    }
}
