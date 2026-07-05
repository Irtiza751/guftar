package com.arbor.guftar.thread.service.dto;

import com.arbor.guftar.thread.service.valueobjects.MediaType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateThreadRequest(
        @NotBlank(message = "content is required")
        String content,
        @Valid List<UpdateThreadMedia> medias
) {
        public record UpdateThreadMedia(
                Long id,
                @NotBlank(message = "Url is required field")
                String url,
                @NotNull(message = "Media type is required field")
                MediaType type,
                Integer position
        ) {}
}
