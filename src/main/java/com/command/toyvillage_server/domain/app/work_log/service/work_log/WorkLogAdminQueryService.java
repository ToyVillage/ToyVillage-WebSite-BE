package com.command.toyvillage_server.domain.app.work_log.service.work_log;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogAnswer;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogDetailResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogSectionAnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkLogAdminQueryService {
    private final WorkLogRepository workLogRepository;

    @Transactional(readOnly = true)
    public WorkLogDetailResponse execute(Long workLogId) {
        WorkLog workLog = workLogRepository.findById(workLogId)
            .orElseThrow(() -> WorkLogNotFoundException.EXCEPTION);

        Map<Long, List<WorkLogAnswer>> answersBySection = workLog.getAnswers().stream()
            .collect(Collectors.groupingBy(answer -> answer.getSection().getId()));

        List<WorkLogSectionAnswerResponse> sections = workLog.getTemplate().getSections().stream()
            .map(section -> WorkLogSectionAnswerResponse.of(
                section,
                answersBySection.getOrDefault(section.getId(), List.of())
            ))
            .toList();

        return WorkLogDetailResponse.of(workLog, sections);
    }
}
