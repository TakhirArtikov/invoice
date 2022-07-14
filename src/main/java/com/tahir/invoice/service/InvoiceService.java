package com.tahir.invoice.service;

import com.tahir.invoice.model.dto.ExpiredInvoice;
import com.tahir.invoice.model.dto.WrongDateInvoice;

import java.util.List;

public interface InvoiceService {

    List<ExpiredInvoice> expiredInvoiceList();
    List<WrongDateInvoice> wrongDateInvoiceList();
}
