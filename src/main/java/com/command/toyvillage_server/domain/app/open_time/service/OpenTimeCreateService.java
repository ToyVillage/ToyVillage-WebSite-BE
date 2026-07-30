package com.command.toyvillage_server.domain.app.open_time.service;

import com.command.toyvillage_server.domain.app.open_time.domain.OpenTime;
import com.command.toyvillage_server.domain.app.open_time.domain.repository.OpenTimeRepository;
import com.command.toyvillage_server.domain.app.open_time.presentation.dto.request.OpenTimeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OpenTimeCreateService {
    private final OpenTimeRepository openTimeRepository;

    @Transactional
    public void execute(OpenTimeCreateRequest request) {
        OpenTime openTime = OpenTime.create(request.openDate());

        openTimeRepository.save(openTime);
    }
}
