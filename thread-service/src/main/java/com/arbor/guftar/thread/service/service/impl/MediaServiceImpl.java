package com.arbor.guftar.thread.service.service.impl;

import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.thread.service.repository.ThreadMediaRepository;
import com.arbor.guftar.thread.service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final ThreadMediaRepository threadMediaRepository;


    @Override
    public SuccessResponse delete(long mediaId) {
        threadMediaRepository.deleteById(mediaId);
        return new SuccessResponse(HttpStatus.OK.name(), "Media has been deleted with id: " + mediaId);
    }
}
