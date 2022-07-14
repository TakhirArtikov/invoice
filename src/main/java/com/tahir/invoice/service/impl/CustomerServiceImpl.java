package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.CustomerDto;
import com.tahir.invoice.model.entity.Customer;
import com.tahir.invoice.model.entity.Order;
import com.tahir.invoice.repository.CustomerRepository;
import com.tahir.invoice.repository.OrderRepository;
import com.tahir.invoice.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    @Override
    public List<CustomerDto> customersWithoutOrders() {
        Set<Long> customersIdList=new HashSet<>();
        List<CustomerDto> customerDtos=new ArrayList<>();
        List<Customer> customersEntity=customerRepository.findAll();
        List<Order> orderEntity=orderRepository.findAll();

        for (int i = 0; i < customersEntity.size(); i++) {
            customersIdList.add(customersEntity.get(i).getId());
        }
        CustomerDto customer=new CustomerDto();

        for (int i = 0; i < customersIdList.size(); i++) {
            if (customersIdList.contains(orderEntity.get(i).getCustId()) &&
                    orderEntity.get(i).getDate().getYear()!=2016){

                customer.setId(customersEntity.get(i).getId());
                customer.setName(customersEntity.get(i).getName());
                customer.setCountry(customersEntity.get(i).getCountry());
                customer.setAddress(customersEntity.get(i).getAddress());
                customer.setPhone(customersEntity.get(i).getPhone());
                customerDtos.add(customer);

            }
        }

        return customerDtos;
    }
}
