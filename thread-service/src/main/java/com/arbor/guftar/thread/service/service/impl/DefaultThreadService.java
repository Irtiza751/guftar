package com.arbor.guftar.thread.service.service.impl;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.dto.CreateThreadRequest;
import com.arbor.guftar.thread.service.dto.ThreadMediaResponseDto;
import com.arbor.guftar.thread.service.dto.ThreadResponseDto;
import com.arbor.guftar.thread.service.entity.Thread;
import com.arbor.guftar.thread.service.entity.ThreadMedia;
import com.arbor.guftar.thread.service.repository.ThreadRepository;
import com.arbor.guftar.thread.service.service.ThreadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class DefaultThreadService implements ThreadService {

    private final ThreadRepository threadRepository;

    @Transactional
    @Override
    public ThreadResponseDto create(CreateThreadRequest threadRequest) {
        Thread thread = Thread.builder().content(threadRequest.content()).userId(threadRequest.userId()).build();
        if (threadRequest.threadMediaDto() != null) {
            ThreadMedia threadMedia = ThreadMedia.builder()
                    .url(threadRequest.threadMediaDto().url())
                    .position(threadRequest.threadMediaDto().position())
                    .type(threadRequest.threadMediaDto().type())
                    .build();

            thread.addThreadMedia(threadMedia);
        }

        if (threadRequest.parentId() != null) {
            Thread parent = threadRepository.findById(threadRequest.parentId())
                    .orElseThrow(() -> new EntityNotFoundException("Thread not found with id: " + threadRequest.parentId()));
            parent.addChildThread(thread);
        }

        Thread savedThread = threadRepository.save(thread);
        return mapThreadToThreadResponseDto(savedThread);
    }

    @Override
    public ThreadResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PaginatedResponse<ThreadResponseDto> findByUserId(Long userId, Pageable pageable) {
        Page<ThreadResponseDto> threads = threadRepository.findByUserId(userId, pageable).map(this::mapThreadToThreadResponseDto);

        return PaginatedResponse.<ThreadResponseDto>builder()
                .data(threads.getContent())
                .currentPage(threads.getNumber())
                .totalPages(threads.getTotalPages())
                .pageSize(threads.getSize())
                .hasNext(threads.hasNext())
                .hasPrevious(threads.hasPrevious())
                .build();
    }

    @Override
    public SuccessResponse update(Long id) {
        return null;
    }

    @Override
    public SuccessResponse delete(Long id) {
        return null;
    }

    @Override
    public PaginatedResponse<ThreadResponseDto> findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ThreadResponseDto> threads = threadRepository.findAll(pageable)
                .map(this::mapThreadToThreadResponseDto);

        return PaginatedResponse.<ThreadResponseDto>builder()
                .data(threads.getContent())
                .currentPage(threads.getNumber())
                .totalPages(threads.getTotalPages())
                .pageSize(threads.getSize())
                .hasNext(threads.hasNext())
                .hasPrevious(threads.hasPrevious())
                .build();
    }

    private ThreadResponseDto mapThreadToThreadResponseDto(Thread thread) {
        return ThreadResponseDto.builder()
                .id(thread.getId())
                .content(thread.getContent())
                .userId(thread.getUserId())
                .parentId(thread.getParent() == null ? null : thread.getParent().getId())
                .medias(thread.getMedias() != null ? thread.getMedias().stream().map(threadMedia -> ThreadMediaResponseDto.builder()
                        .url(threadMedia.getUrl())
                        .type(threadMedia.getType())
                        .position(threadMedia.getPosition())
                        .createAt(threadMedia.getCreatedAt())
                        .updatedAt(threadMedia.getUpdatedAt())
                        .build()).toList() : null)
                .build();
    }
}
