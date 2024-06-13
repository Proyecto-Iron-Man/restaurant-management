package com.ironman.restaurantmanagement.presistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// JPA annotations
@Entity
@Table(name = "tables")
public class Tabla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
