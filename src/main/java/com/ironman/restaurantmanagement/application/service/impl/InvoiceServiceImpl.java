package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceBodyDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceSavedDto;
import com.ironman.restaurantmanagement.application.mapper.InvoiceMapper;
import com.ironman.restaurantmanagement.application.service.InvoiceService;
import com.ironman.restaurantmanagement.presistence.entity.Invoice;
import com.ironman.restaurantmanagement.presistence.repository.InvoiceRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;


    @Override
    public InvoiceDto findById(Long id) throws DataNotFoundException {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDto)
                .orElseThrow(() -> invoiceDataNotFoundException(id));
    }

    @Override
    public InvoiceSavedDto create(InvoiceBodyDto invoiceBody) throws DataNotFoundException {

        Invoice invoice = invoiceMapper.toEntity(invoiceBody);
        invoice.setState(State.ENABLED.getValue());
        invoice.setCreatedAt(LocalDateTime.now());

        invoice.getInvoiceDetails().forEach(detail -> {
            detail.setInvoice(invoice);
            detail.setState(State.ENABLED.getValue());
            detail.setCreatedAt(LocalDateTime.now());
        });

        return invoiceMapper.toSavedDto(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceSavedDto update(Long id, InvoiceBodyDto invoiceBody) throws DataNotFoundException {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> invoiceDataNotFoundException(id));

        invoiceMapper.updateEntity(invoice, invoiceBody);

        invoice.getInvoiceDetails().forEach(detail -> {
            detail.setInvoice(invoice);
            detail.getId().setInvoiceId(invoice.getId());
            detail.setState(State.ENABLED.getValue());
            detail.setCreatedAt(LocalDateTime.now());
            detail.setUpdatedAt(LocalDateTime.now());
        });

        return invoiceMapper.toSavedDto(invoiceRepository.save(invoice));
    }


    private DataNotFoundException invoiceDataNotFoundException(Long id) {
        return new DataNotFoundException("Invoice not found with id: " + id);
    }
}
