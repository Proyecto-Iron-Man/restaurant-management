package com.ironman.restaurantmanagement.presistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

// Lombok annotations
@Getter
@AllArgsConstructor
public enum CategorySortField {
    ID("id", "id"),
    NAME("name", "name"),
    DESCRIPTION("description", "description"),
    STATE("state", "state"),
    CREATED_AT("createdAt", "created_at");

    private final String fieldName;
    private final String columnName;

    public static String getSqlColumn(String value) {
        return Arrays.stream(CategorySortField.values())
                .filter(sortField -> sortField.getFieldName().equals(value))
                .findFirst()
                .map(CategorySortField::getColumnName)
                .orElseGet(ID::getColumnName);
    }

}
