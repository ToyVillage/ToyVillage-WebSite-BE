package com.command.toyvillage_server.domain.app.work_report.domain.repository;

import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
    Optional<WorkReport> findByTask_IdAndTask_Assignee_Id(Long taskId, Long taskAssigneeId);
}
