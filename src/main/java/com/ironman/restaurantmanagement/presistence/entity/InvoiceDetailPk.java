package com.ironman.restaurantmanagement.presistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

// JPA annotations
@Embeddable
public class InvoiceDetailPk implements Serializable {

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "product_id")
    private Long productId;

}
