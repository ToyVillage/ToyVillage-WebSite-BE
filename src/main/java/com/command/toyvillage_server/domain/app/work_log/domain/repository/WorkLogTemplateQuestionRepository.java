package com.command.toyvillage_server.domain.app.work_log.domain.repository;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogTemplateQuestionRepository extends JpaRepository<WorkLogTemplateQuestion, Long> {
}
