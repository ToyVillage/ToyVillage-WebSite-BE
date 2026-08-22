package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogAdminQueryListService {
    private final WorkLogRepository workLogRepository;

    @Transactional(readOnly = true)
    public Page<WorkLogListResponse> execute(Pageable pageable) {
        Pageable latestFirst = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.desc("writeAt"), Sort.Order.desc("id"))
        );

        return workLogRepository.findAll(latestFirst)
                .map(WorkLogListResponse::from);
    }
}
