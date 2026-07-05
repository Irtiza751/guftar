package com.arbor.guftar.thread.service.service.impl;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.dto.CreateThreadRequest;
import com.arbor.guftar.thread.service.dto.ThreadResponseDto;
import com.arbor.guftar.thread.service.dto.UpdateThreadRequest;
import com.arbor.guftar.thread.service.entity.Thread;
import com.arbor.guftar.thread.service.entity.ThreadMedia;
import com.arbor.guftar.thread.service.mapper.ThreadRequestMapper;
import com.arbor.guftar.thread.service.repository.ThreadRepository;
import com.arbor.guftar.thread.service.service.ThreadService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class DefaultThreadService implements ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadRequestMapper threadRequestMapper;

    @Transactional
    @Override
    public ThreadResponseDto create(CreateThreadRequest threadRequest) {
        Thread thread = Thread.builder().content(threadRequest.content()).userId(threadRequest.userId()).build();
        if (threadRequest.medias() != null && !threadRequest.medias().isEmpty()) {
            List<ThreadMedia> threadMediaList = threadRequest.medias().stream().map(threadMediaDto -> ThreadMedia.builder()
                            .url(threadMediaDto.url())
                            .position(threadMediaDto.position() == null ? 0 : threadMediaDto.position())
                            .type(threadMediaDto.type())
                            .build())
                    .toList();

            thread.addThreadMedia(threadMediaList);
        }

        if (threadRequest.parentId() != null) {
            Thread parent = threadRepository.findById(threadRequest.parentId())
                    .orElseThrow(() -> new EntityNotFoundException("Thread not found with id: " + threadRequest.parentId()));
            parent.addChildThread(thread);
        }

        Thread savedThread = threadRepository.save(thread);
        return threadRequestMapper.mapThreadToThreadResponseDto(savedThread);
    }

    @Override
    public ThreadResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PaginatedResponse<ThreadResponseDto> findByUserId(Long userId, Pageable pageable) {
        Page<ThreadResponseDto> threads = threadRepository.findByUserId(userId, pageable)
                .map(threadRequestMapper::mapThreadToThreadResponseDto);

        return PaginatedResponse.<ThreadResponseDto>builder()
                .data(threads.getContent())
                .currentPage(threads.getNumber())
                .totalPages(threads.getTotalPages())
                .pageSize(threads.getSize())
                .hasNext(threads.hasNext())
                .hasPrevious(threads.hasPrevious())
                .build();
    }

    @Transactional
    @Override
    public ThreadResponseDto update(Long id, @Valid UpdateThreadRequest updateThreadRequest) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thread not found with id: " + id));

        threadRequestMapper.mapUpdateThreadRequestToThread(updateThreadRequest, thread);

        Thread savedThread = threadRepository.save(thread);
        return threadRequestMapper.mapThreadToThreadResponseDto(savedThread);
    }

    @Override
    public SuccessResponse delete(Long id) {
        threadRepository.deleteById(id);
        return new SuccessResponse(HttpStatus.OK.name(), "Thread with id: " + id + " has been deleted");
    }

    @Override
    public PaginatedResponse<ThreadResponseDto> findAll(Pageable pageable) {
        Page<ThreadResponseDto> threads = threadRepository.findAll(pageable)
                .map(threadRequestMapper::mapThreadToThreadResponseDto);

        return PaginatedResponse.<ThreadResponseDto>builder()
                .data(threads.getContent())
                .currentPage(threads.getNumber())
                .totalPages(threads.getTotalPages())
                .pageSize(threads.getSize())
                .hasNext(threads.hasNext())
                .hasPrevious(threads.hasPrevious())
                .build();
    }
}
