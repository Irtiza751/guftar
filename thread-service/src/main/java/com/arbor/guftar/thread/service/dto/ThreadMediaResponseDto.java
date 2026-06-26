package com.arbor.guftar.thread.service.dto;

import com.arbor.guftar.thread.service.valueobjects.MediaType;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record ThreadMediaResponseDto (
        Long id,
        String url,
        MediaType type,
        Integer position,
        OffsetDateTime createAt,
        OffsetDateTime updatedAt
) {
}
