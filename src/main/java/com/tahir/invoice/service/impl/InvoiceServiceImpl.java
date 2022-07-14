package com.tahir.invoice.service.impl;

import com.tahir.invoice.model.dto.ExpiredInvoice;
import com.tahir.invoice.model.dto.WrongDateInvoice;
import com.tahir.invoice.model.entity.Customer;
import com.tahir.invoice.model.entity.Invoice;
import com.tahir.invoice.model.entity.Order;
import com.tahir.invoice.repository.CustomerRepository;
import com.tahir.invoice.repository.InvoiceRepository;
import com.tahir.invoice.repository.OrderRepository;
import com.tahir.invoice.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<ExpiredInvoice> expiredInvoiceList() {
        ExpiredInvoice expiredInvoice = new ExpiredInvoice();

        List<ExpiredInvoice> list = new ArrayList<>();
        List<Invoice> invoicesEntity = invoiceRepository.findAll();
        for (Invoice invoice : invoicesEntity) {
            if (invoice.getDue().isBefore(LocalDate.now())) {
                Optional<Order> order = orderRepository.findById(invoice.getOrdId());
                if (order.isPresent()) {
                    Optional<Customer> customer = customerRepository.findById(order.get().getCustId());
                    if (customer.isPresent()) {
                        expiredInvoice.setCustName(customer.get().getName());
                        expiredInvoice.setAmount(invoice.getAmount());
                        expiredInvoice.setIssued(invoice.getIssued());
                        expiredInvoice.setDue(invoice.getDue());
                        list.add(expiredInvoice);
                    } else {
                        throw new NullPointerException("Customer does not exist!");
                    }
                } else {
                    throw new NullPointerException("Order does not exist!");
                }
            }
        }
        return list;
    }

    @Override
    public List<WrongDateInvoice> wrongDateInvoiceList() {
        WrongDateInvoice wrongDateInvoice=new WrongDateInvoice();
        List<WrongDateInvoice> list=new ArrayList<>();
        List<Invoice> invoiceList=invoiceRepository.findAll();


        for (int i = 0; i < invoiceList.size(); i++) {
            Optional<Order> order=orderRepository.findById(invoiceList.get(i).getOrdId());
            if (order.isPresent()) {
                if (invoiceList.get(i).getIssued().isBefore(order.get().getDate())){
                    wrongDateInvoice.setInvoiceId(invoiceList.get(i).getId());
                    wrongDateInvoice.setIssuedDate(invoiceList.get(i).getIssued());
                    wrongDateInvoice.setOrderDate(order.get().getDate());
                    wrongDateInvoice.setOrderId(invoiceList.get(i).getOrdId());

                    list.add(wrongDateInvoice);
                }
            }
        }
        return list;
    }
}
