package com.ironman.restaurantmanagement.presistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
CREATE TABLE public."tables" (
	id serial4 NOT NULL,
	name varchar(200) NOT NULL,
	description varchar(1000) NULL,
	state bpchar(1) DEFAULT 'A'::bpchar NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT tables_name_uq UNIQUE (name),
	CONSTRAINT tables_pk PRIMARY KEY (id)
);
* */

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
