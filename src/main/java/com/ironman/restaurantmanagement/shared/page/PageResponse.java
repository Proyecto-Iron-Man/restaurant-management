package com.ironman.restaurantmanagement.shared.page;

import lombok.*;

import java.util.List;

// Lombok annotations
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private Integer number;
    private Integer numberOfElements;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
}
