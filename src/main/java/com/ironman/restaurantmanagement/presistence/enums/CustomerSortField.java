package com.ironman.restaurantmanagement.presistence.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

// Lombok annotations
@RequiredArgsConstructor
@Getter
public enum CustomerSortField {
    ID("id", "id"),
    NAME("name", "name"),
    LAST_NAME("lastName", "last_name"),
    DOCUMENT_TYPE_ID("documentTypeId", "document_type_id"),
    CREATED_AT("createdAt", "created_at");

    private final String fieldName;
    private final String columnName;

    public static String getSqlColumn(String value) {
        return Arrays.stream(CustomerSortField.values())
                .filter(sortField -> sortField.getFieldName().equals(value))
                .findFirst()
                .map(CustomerSortField::getColumnName)
                .orElseGet(ID::getColumnName);
    }
}
