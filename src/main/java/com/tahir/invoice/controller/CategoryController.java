package com.tahir.invoice.controller;

import com.tahir.invoice.model.dto.CategoryDto;
import com.tahir.invoice.model.entity.Category;
import com.tahir.invoice.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;


    @Operation(summary = "Category List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))))
    })
    @GetMapping("/list")
    private List<CategoryDto> getList(){
        return categoryService.getList();
    }

    @Operation(summary = "Category details by product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))))
    })
    @GetMapping("/getCategoryDetails")
    private CategoryDto getCategoryDetails(@RequestParam(value = "product_id") Long productId){
        return categoryService.getProductById(productId);
    }


}
