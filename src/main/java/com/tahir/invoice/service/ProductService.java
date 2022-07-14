package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductList();
    ProductDto getProductDetailsById(Long productId);
}
