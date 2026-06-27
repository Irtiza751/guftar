package com.arbor.guftar.thread.service.controller;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.dto.CreateThreadRequest;
import com.arbor.guftar.thread.service.dto.ThreadResponseDto;
import com.arbor.guftar.thread.service.service.ThreadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/threads")
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadService threadService;

    @PostMapping
    public ResponseEntity<ThreadResponseDto> create(@Valid @RequestBody CreateThreadRequest threadRequest) {
        return new ResponseEntity<>(threadService.create(threadRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<ThreadResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(threadService.findAll(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ThreadResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(threadService.findById(id));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PaginatedResponse<ThreadResponseDto>> findByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(threadService.findByUserId(userId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable Long id) {
        return ResponseEntity.ok(threadService.update(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(threadService.delete(id));
    }
}
