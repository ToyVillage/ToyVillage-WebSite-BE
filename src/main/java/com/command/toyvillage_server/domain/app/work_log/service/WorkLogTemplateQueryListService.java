package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateQuestionRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateQueryListService {
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final WorkLogTemplateQuestionRepository workLogTemplateQuestionRepository;

    @Transactional(readOnly = true)
    public
}
