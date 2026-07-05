package com.arbor.guftar.thread.service.dto;

import com.arbor.guftar.thread.service.valueobjects.MediaType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ThreadMediaDto(
        @NotBlank(message = "Url is required field")
        String url,
        @NotNull(message = "Media type is required field")
        MediaType type,
        @Nullable
        Integer position
) { }
