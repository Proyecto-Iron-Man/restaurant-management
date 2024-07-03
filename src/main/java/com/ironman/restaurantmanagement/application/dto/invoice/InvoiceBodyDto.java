package com.ironman.restaurantmanagement.application.dto.invoice;

import com.ironman.restaurantmanagement.application.dto.invoicedetail.InvoiceDetailBodyDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class InvoiceBodyDto {
    @NotNull(message = "Invoice date is required")
    private LocalDateTime invoiceDate;

    @NotNull(message = "Customer id is required")
    @Positive(message = "Customer id must be positive")
    private Long customerId;

    @NotNull(message = "User id is required")
    @Positive(message = "User id must be positive")
    private Long userId;

    @Valid
    private List<InvoiceDetailBodyDto> invoiceDetails;
}
