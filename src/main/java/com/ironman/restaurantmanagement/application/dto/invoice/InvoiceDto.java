package com.ironman.restaurantmanagement.application.dto.invoice;

import com.ironman.restaurantmanagement.application.dto.customer.CustomerSmallDto;
import com.ironman.restaurantmanagement.application.dto.invoicedetail.InvoiceDetailDto;
import com.ironman.restaurantmanagement.application.dto.user.UserSmallDto;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private LocalDateTime invoiceDate;
    private CustomerSmallDto customer;
    private UserSmallDto user;
    private List<InvoiceDetailDto> invoiceDetails;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
