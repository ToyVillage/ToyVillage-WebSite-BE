package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateQuestionRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateWriteService {
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final WorkLogTemplateQuestionRepository workLogTemplateQuestionRepository;

    @Transactional
    public void execute(WorkLogTemplateWriteRequest request, Long workLogTemplateId) {
        WorkLogTemplate workLogTemplate = workLogTemplateRepository.findById(workLogTemplateId)
            .orElseThrow(() -> WorkLogTemplateNotFoundException.EXCEPTION);

        List<WorkLogTemplateQuestion> questionList = workLogTemplateQuestionRepository.findByWorkLogTemplateId(workLogTemplateId);

        questionList.forEach(question -> {
        });
    }
}
