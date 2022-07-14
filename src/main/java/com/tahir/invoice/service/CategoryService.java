package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.CategoryDto;
import com.tahir.invoice.model.entity.Product;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getList();

    CategoryDto getProductById(Long productId);
}
