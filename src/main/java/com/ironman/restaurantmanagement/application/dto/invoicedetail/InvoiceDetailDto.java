package com.ironman.restaurantmanagement.application.dto.invoicedetail;

import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailDto {
    private Integer quantity;
    private BigDecimal price;
    private ProductSmallDto product;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
