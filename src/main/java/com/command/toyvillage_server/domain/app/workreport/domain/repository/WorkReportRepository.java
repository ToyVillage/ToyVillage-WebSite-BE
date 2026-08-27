package com.command.toyvillage_server.domain.app.workreport.domain.repository;

import com.command.toyvillage_server.domain.app.workreport.domain.Status;
import com.command.toyvillage_server.domain.app.workreport.domain.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
    Optional<WorkReport> findByTask_IdAndTask_Assignee_Id(Long taskId, Long taskAssigneeId);
    List<WorkReport> findAllByStatus(Status status);
}
