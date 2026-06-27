package com.arbor.guftar.thread.service.dto;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record ThreadResponseDto(
        Long id,
        String content,
        Long userId,
        Long parentId,
        List<ThreadMediaResponseDto> medias,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) { }

