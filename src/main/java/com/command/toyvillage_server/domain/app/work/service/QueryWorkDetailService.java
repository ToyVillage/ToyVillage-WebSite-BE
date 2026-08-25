package com.command.toyvillage_server.domain.app.work.service;

import com.command.toyvillage_server.domain.app.work.domain.Work;
import com.command.toyvillage_server.domain.app.work.domain.repository.WorkRepository;
import com.command.toyvillage_server.domain.app.work.exception.WorkNotFoundException;
import com.command.toyvillage_server.domain.app.work.presentation.dto.response.WorkDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryWorkDetailService {

    private final WorkRepository workRepository;

    @Transactional(readOnly = true)
    public WorkDetailResponse execute(Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        return WorkDetailResponse.from(work);

    }

}
