package com.command.toyvillage_server.domain.app.task.service;

import com.command.toyvillage_server.domain.app.auth.admin.facade.UserFacade;
import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.task.presentation.dto.response.TaskListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryMyTaskListService {
    private final TaskRepository taskRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public TaskListResponse execute(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllAssignedTo(userFacade.getCurrentUserId(), pageable);

        return TaskListResponse.from(tasks);
    }
}
