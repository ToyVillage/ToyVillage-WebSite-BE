package com.command.toyvillage_server.domain.app.work_log.domain.repository;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkLogTemplateRepository extends JpaRepository<WorkLogTemplate, Long> {
    boolean existsByTemplateTitle(String templateTitle);

    Page<WorkLogTemplate> findAllByDeleteYnFalseAndCreatedAtOrderByIdDesc(Pageable pageable, LocalDate createdAt);
}
