package com.arbor.guftar.thread.service.service;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.dto.CreateThreadRequest;
import com.arbor.guftar.thread.service.dto.ThreadResponseDto;
import com.arbor.guftar.thread.service.dto.UpdateThreadRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;


public interface ThreadService {
    ThreadResponseDto create(CreateThreadRequest threadRequest);
    ThreadResponseDto findById(Long id);
    PaginatedResponse<ThreadResponseDto> findByUserId(Long userId, Pageable pageable);
    ThreadResponseDto update(Long id, UpdateThreadRequest updateThreadRequest);
    SuccessResponse delete(Long id);
    PaginatedResponse<ThreadResponseDto> findAll(Pageable pageable);
}
