package com.ironman.restaurantmanagement.presistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// JPA annotations
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "document_type_id")
    private Long documentTypeId;

    @ManyToOne
    @JoinColumn(name = "document_type_id", insertable = false, updatable = false)
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
