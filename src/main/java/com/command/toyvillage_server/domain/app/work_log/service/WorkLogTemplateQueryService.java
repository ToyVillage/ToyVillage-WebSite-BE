package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateQueryService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional(readOnly = true)
    public WorkLogTemplateDetailResponse execute(Long workLogTemplateId) {
        WorkLogTemplate workLogTemplate = workLogTemplateRepository.findById(workLogTemplateId)
                .orElseThrow(() -> WorkLogTemplateNotFoundException.EXCEPTION);

        return WorkLogTemplateDetailResponse.from(workLogTemplate);
    }
}
