package com.arbor.guftar.thread.service.mapper;

import com.arbor.guftar.thread.service.dto.ThreadMediaResponseDto;
import com.arbor.guftar.thread.service.dto.ThreadResponseDto;
import com.arbor.guftar.thread.service.dto.UpdateThreadRequest;
import com.arbor.guftar.thread.service.entity.Thread;
import com.arbor.guftar.thread.service.entity.ThreadMedia;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ThreadRequestMapper {

    public void mapUpdateThreadRequestToThread(UpdateThreadRequest request, Thread thread) {
        if(request.content() != null && !request.content().isBlank()) {
            thread.setContent(request.content());
        }

        if(request.medias() == null || request.medias().isEmpty()) {
            return;
        }

        for (UpdateThreadRequest.UpdateThreadMedia media : request.medias()) {

            int position = media.position() == null ? 0 : media.position();
            if(media.id() ==  null) {
                ThreadMedia threadMedia = ThreadMedia.builder()
                        .type(media.type())
                        .url(media.url())
                        .position(position)
                        .build();
                thread.addThreadMedia(threadMedia);
            } else {
                ThreadMedia existingMedia = thread.getMedias()
                        .stream()
                        .filter(m -> Objects.equals(m.getId(), media.id()))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Media not found with id: " + media.id()));
                existingMedia.setUrl(media.url());
                existingMedia.setType(media.type());
                existingMedia.setPosition(position);
            }
        }
    }

    public ThreadResponseDto mapThreadToThreadResponseDto(@NotNull Thread thread) {
        return ThreadResponseDto.builder()
                .id(thread.getId())
                .content(thread.getContent())
                .userId(thread.getUserId())
                .parentId(thread.getParent() == null ? null : thread.getParent().getId())
                .medias(thread.getMedias() != null ? thread.getMedias().stream()
                        .map(threadMedia -> ThreadMediaResponseDto.builder()
                        .id(threadMedia.getId())
                        .url(threadMedia.getUrl())
                        .type(threadMedia.getType())
                        .position(threadMedia.getPosition())
                        .threadId(thread.getParent() == null ? null : thread.getParent().getId())
                        .createAt(threadMedia.getCreatedAt())
                        .updatedAt(threadMedia.getUpdatedAt())
                        .build()).toList() : null)
                .createdAt(thread.getCreatedAt())
                .updatedAt(thread.getUpdatedAt())
                .build();
    }
}
