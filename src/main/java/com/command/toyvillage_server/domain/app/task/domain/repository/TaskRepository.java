package com.command.toyvillage_server.domain.app.task.domain.repository;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
