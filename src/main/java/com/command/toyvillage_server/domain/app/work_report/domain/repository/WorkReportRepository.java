package com.command.toyvillage_server.domain.app.work_report.domain.repository;

import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.Visibility;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {

    @EntityGraph(attributePaths = "employee")
    List<WorkReport> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = "employee")
    List<WorkReport> findAllByVisibilityOrderByIdDesc(Visibility visibility);

    @EntityGraph(attributePaths = "employee")
    List<WorkReport> findAllByVisibilityOrVisibilityAndVisibleTeam_IdOrderByIdDesc(
            Visibility allVisibility,
            Visibility teamVisibility,
            Long teamId
    );
}
