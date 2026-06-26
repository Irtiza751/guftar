package com.arbor.guftar.thread.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/threads")
@RequiredArgsConstructor
public class ThreadController {

    @PostMapping
    public ResponseEntity<?> create() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id) {
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> patch() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return null;
    }
}
