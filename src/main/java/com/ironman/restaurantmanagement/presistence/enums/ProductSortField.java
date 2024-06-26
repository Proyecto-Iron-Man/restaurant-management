package com.ironman.restaurantmanagement.presistence.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

// Lombok annotations
@RequiredArgsConstructor
@Getter
public enum ProductSortField {
    ID("id", "id"),
    NAME("name", "name"),
    DESCRIPTION("description", "description"),
    CATEGORY_ID("categoryId", "category_id"),
    STOCK("stock", "stock"),
    STATE("state", "state"),
    CREATED_AT("createdAt", "created_at");

    private final String fieldName;
    private final String columnName;

    public static String getSqlColumn(String value) {
        return Arrays.stream(ProductSortField.values())
                .filter(sortField -> sortField.getFieldName().equals(value))
                .findFirst()
                .map(ProductSortField::getColumnName)
                .orElseGet(ID::getColumnName);
    }

}
