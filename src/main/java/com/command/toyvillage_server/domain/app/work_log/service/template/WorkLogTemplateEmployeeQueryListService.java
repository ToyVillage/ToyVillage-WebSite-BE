package com.command.toyvillage_server.domain.app.work_log.service.template;

import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateEmployeeQueryListService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional(readOnly = true)
    public Page<WorkLogTemplateQueryListObjectResponse> execute(Pageable p) {
        return workLogTemplateRepository.findAllByOrderByIdDesc(p)
            .map(WorkLogTemplateQueryListObjectResponse::from);
    }
}
