package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.CategoryDto;
import com.tahir.invoice.model.dto.ProductDto;
import com.tahir.invoice.model.entity.Category;
import com.tahir.invoice.model.entity.Product;
import com.tahir.invoice.repository.CategoryRepository;
import com.tahir.invoice.repository.ProductRepository;
import com.tahir.invoice.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    @Override
    public List<CategoryDto> getList() {
       List<Category> categoryEntity=categoryRepository.findAll();
       List<CategoryDto> categoryDtos=new ArrayList<>();
       categoryEntity.forEach(category -> {
           CategoryDto dto=new CategoryDto();
           BeanUtils.copyProperties(category,dto);
           categoryDtos.add(dto);
       });
       return categoryDtos;
    }

    @Override
    public CategoryDto getProductById(Long productId) {
       Optional<Product> product=productRepository.findById(productId);
       if (product.isPresent()){
           Long categoryId=product.get().getCategoryId();
           Optional<Category> categoryEntity=categoryRepository.findById(categoryId);
           if (categoryEntity.isPresent()){
               Category category=categoryEntity.get();
               CategoryDto categoryDto=new CategoryDto();
               BeanUtils.copyProperties(category,categoryDto);
               return categoryDto;
           }
       }
       return null;
    }
}
