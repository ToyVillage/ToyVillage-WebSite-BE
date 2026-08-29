package com.command.toyvillage_server.domain.app.task.service;

import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.task.presentation.dto.response.TaskListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryTaskListService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public TaskListResponse execute(Pageable pageable) {
        return TaskListResponse.from(taskRepository.findAll(pageable));
    }
}
