package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateQueryListService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional(readOnly = true)
    public Page<WorkLogTemplateListResponse> execute(Pageable pageable) {
        Pageable latestFirst = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id"))
        );

        return workLogTemplateRepository.findAll(latestFirst)
                .map(WorkLogTemplateListResponse::from);
    }
}
