package com.arbor.guftar.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import org.springframework.data.domain.Page;

@Getter
@Builder
@AllArgsConstructor
public class PaginatedResponse<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
    private boolean hasNext;
    private boolean hasPrevious;

    public static <T> PaginatedResponse<T> from(Page<T> pagedData) {
        return PaginatedResponse.<T>builder()
                .data(pagedData.getContent())
                .currentPage(pagedData.getNumber())
                .totalPages(pagedData.getTotalPages())
                .pageSize(pagedData.getSize())
                .hasNext(pagedData.hasNext())
                .hasPrevious(pagedData.hasPrevious())
                .totalItems(pagedData.getTotalElements())
                .build();
    }
}
