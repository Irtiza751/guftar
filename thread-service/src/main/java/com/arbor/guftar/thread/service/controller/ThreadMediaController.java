package com.arbor.guftar.thread.service.controller;

import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class ThreadMediaController {

    private final MediaService mediaService;

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable long mediaId) {
        return ResponseEntity.ok(mediaService.delete(mediaId));
    }

}
