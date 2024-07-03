package com.ironman.restaurantmanagement.presistence.repository;

import com.ironman.restaurantmanagement.presistence.entity.Invoice;
import org.springframework.data.repository.ListCrudRepository;

public interface InvoiceRepository extends ListCrudRepository<Invoice, Long> {
}
