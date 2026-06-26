package com.arbor.guftar.thread.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateThreadRequest(
        @NotBlank(message = "Content is required field")
        String content,
        @NotNull(message = "User id is required field")
        Long userId,
        @Valid
        ThreadMediaDto threadMediaDto,
        Long parentId
) {
}
