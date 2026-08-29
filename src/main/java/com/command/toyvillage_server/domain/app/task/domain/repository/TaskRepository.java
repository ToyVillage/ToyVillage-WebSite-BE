package com.command.toyvillage_server.domain.app.task.domain.repository;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
            select t from Task t
            where t.assigneeType = com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType.ALL
               or t.assignee.id = :appAdminId
               or exists (
                   select jt.id from JoinTeam jt
                   where jt.team.id = t.assigneeTeam.id
                     and jt.appAdmin.id = :appAdminId
               )
            """)
    Page<Task> findAllAssignedTo(
            @Param("appAdminId") Long appAdminId,
            Pageable pageable
    );
}
