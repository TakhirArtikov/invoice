package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.OrderDto;
import com.tahir.invoice.model.dto.request.CreateOrder;
import com.tahir.invoice.model.dto.response.OrderDetailResponse;
import com.tahir.invoice.model.dto.response.ResponseOrder;
import com.tahir.invoice.model.entity.*;
import com.tahir.invoice.repository.*;
import com.tahir.invoice.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private DetailRepository detailRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private InvoiceRepository invoiceRepository;
    @Override
    public OrderDetailResponse getOrderDetails(Long orderId) {
        OrderDetailResponse orderDetailResponse= new OrderDetailResponse();

        //getting detail quantity and product name
        Optional<Detail> detailEntity=detailRepository.findByOrdId(orderId);
        if (detailEntity.isPresent()) {
            orderDetailResponse.setQuantity(detailEntity.get().getQuantity());
            Long prId = detailEntity.get().getPrId();
            Optional<Product> productEntity = productRepository.findById(prId);
            productEntity.ifPresent(product -> orderDetailResponse.setProductName(product.getName()));
        }

        //getting customer name from customer table
        Optional<Order> orderEntity=orderRepository.findById(orderId);
        if (orderEntity.isPresent()){
            orderDetailResponse.setId(orderId);
            orderDetailResponse.setDate(orderEntity.get().getDate());
            Long customerId=orderEntity.get().getCustId();
            Optional<Customer> customerEntity=customerRepository.findById(customerId);
            customerEntity.ifPresent(customer -> orderDetailResponse.setCustomerName(customer.getName()));
        }

        //getting order amount from invoice table

        Optional<Invoice> invoiceEntity=invoiceRepository.findByOrdId(orderId);
        invoiceEntity.ifPresent(invoice -> orderDetailResponse.setAmount(invoice.getAmount()));

        return orderDetailResponse;
    }

    @Override
    public ResponseOrder createOrder(CreateOrder dto) {
        ResponseOrder responseOrder=new ResponseOrder();
        Optional<Product> productEntity=productRepository.findById(dto.getProductId());
        if (productEntity.isEmpty()){
            responseOrder.setStatus("FAILED");
            responseOrder.setInvoiceNumber(-1L);
            return responseOrder;
        }
        Optional<Customer> customerEntity=customerRepository.findById(dto.getCustomerId());
        if (customerEntity.isEmpty()){
            responseOrder.setStatus("FAILED");
            responseOrder.setInvoiceNumber(-1L);
            return responseOrder;
        }

        Order orderEntity=new Order();
        orderEntity.setCustId(dto.getCustomerId());
        orderEntity.setDate(LocalDate.now());
        orderRepository.saveAndFlush(orderEntity);

        Detail detailEntity=new Detail();

        detailEntity.setQuantity(dto.getQuantity());
        detailEntity.setPrId(dto.getProductId());
        detailEntity.setOrdId(orderEntity.getId());
        detailRepository.saveAndFlush(detailEntity);

        Invoice invoiceEntity=new Invoice();

        invoiceEntity.setOrdId(orderEntity.getId());
        invoiceEntity.setAmount(dto.getQuantity()*productEntity.get().getPrice());
        invoiceEntity.setIssued(LocalDate.now());
        invoiceEntity.setDue(LocalDate.now().plusDays(3));
        invoiceRepository.saveAndFlush(invoiceEntity);

        responseOrder.setStatus("SUCCESS");
        responseOrder.setInvoiceNumber(invoiceEntity.getId());
        return responseOrder;
    }

    @Override
    public List<OrderDto> ordersWithoutDetails() {

        List<OrderDto> orderWithoutDetails= new ArrayList<>();

        List<Detail> detailList=detailRepository.findAll();

        List<Order> orderList=orderRepository.findAll();

        Set<Long> detailOrdId= new HashSet<>();

        for (Detail detail : detailList) {
            detailOrdId.add(detail.getOrdId());
        }
        for (Order order : orderList) {
            if(!detailOrdId.contains(order.getId()) &&
                    order.getDate().isBefore(LocalDate.of(2016,9,6))){
                OrderDto orderDto = new OrderDto();
                orderDto.setId(order.getId());
                orderDto.setDate(order.getDate());
                orderWithoutDetails.add(orderDto);
            }
        }
        return orderWithoutDetails;
    }
}
