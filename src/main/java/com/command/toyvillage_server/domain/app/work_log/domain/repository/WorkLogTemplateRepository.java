package com.command.toyvillage_server.domain.app.work_log.domain.repository;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogTemplateRepository extends JpaRepository<WorkLogTemplate, Long> {
    boolean existsByTemplateTitle(String templateTitle);
}
