package com.command.toyvillage_server.domain.app.work_log.domain.repository;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    @Override
    @EntityGraph(attributePaths = "appAdmin")
    Page<WorkLog> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "appAdmin")
    Page<WorkLog> findAllByAppAdmin_Id(Long appAdminId, Pageable pageable);
}
