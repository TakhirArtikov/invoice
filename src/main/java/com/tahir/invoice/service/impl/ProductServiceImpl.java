package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.ProductDto;
import com.tahir.invoice.model.entity.Product;
import com.tahir.invoice.repository.DetailRepository;
import com.tahir.invoice.repository.ProductRepository;
import com.tahir.invoice.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DetailRepository detailRepository;

    @Override
    public List<ProductDto> getProductList() {
        List<Product> productEntity=productRepository.findAll();
        List<ProductDto> productDtos=new ArrayList<>();
        productEntity.forEach(entity->{
            ProductDto dto=new ProductDto();
            BeanUtils.copyProperties(entity,dto);
            productDtos.add(dto);

        });

        return productDtos;
    }

    @Override
    public ProductDto getProductDetailsById(Long productId) {
        Optional<Product> productEntity=productRepository.findById(productId);
        if (productEntity.isPresent()){
           Product entity=productEntity.get();
           ProductDto dto= new ProductDto();
           BeanUtils.copyProperties(entity,dto);
           return dto;
        }


        return null;
    }
}
