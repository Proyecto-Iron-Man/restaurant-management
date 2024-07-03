package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceBodyDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceDto;
import com.ironman.restaurantmanagement.application.dto.invoice.InvoiceSavedDto;
import com.ironman.restaurantmanagement.application.service.InvoiceService;
import com.ironman.restaurantmanagement.shared.constant.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lombok annotations
@RequiredArgsConstructor

// Spring Stereotype annotation
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @ApiResponse(responseCode = StatusCode.OK, description = "Invoice by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Invoice not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceService.findById(id));
    }


    @ApiResponse(responseCode = StatusCode.CREATED, description = "Invoice created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "User, Customer or Product not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PostMapping
    public ResponseEntity<InvoiceSavedDto> create(@Valid @RequestBody InvoiceBodyDto invoiceBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoiceService.create(invoiceBody));
    }
}
