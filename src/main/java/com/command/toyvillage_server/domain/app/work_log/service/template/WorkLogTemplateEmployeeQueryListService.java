package com.command.toyvillage_server.domain.app.work_log.service.template;

import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListObjectResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateEmployeeQueryListService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional(readOnly = true)
    public WorkLogTemplateQueryListResponse execute() {
        List<WorkLogTemplateQueryListObjectResponse> templates =
            workLogTemplateRepository.findAllByOrderByIdDesc()
                .stream()
                .map(WorkLogTemplateQueryListObjectResponse::from)
                .toList();

        return WorkLogTemplateQueryListResponse.from(templates);
    }
}
