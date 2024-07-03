package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceBodyDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceSavedDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;

public interface InvoiceService {

    InvoiceDto findById(Long id) throws DataNotFoundException;

    InvoiceSavedDto create(InvoiceBodyDto invoiceBody) throws DataNotFoundException;

    InvoiceSavedDto update(Long id, InvoiceBodyDto invoiceBody) throws DataNotFoundException;
}
