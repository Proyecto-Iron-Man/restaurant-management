package com.ironman.restaurantmanagement.presistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// JPA annotations
@Entity
@Table(name = "invoice_details")
public class InvoiceDetail implements Serializable {
    @EmbeddedId
    private InvoiceDetailPk id;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private Invoice invoice;

    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
