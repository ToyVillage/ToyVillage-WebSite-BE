package com.command.toyvillage_server.domain.app.task.service;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.task.exception.TaskNotFoundException;
import com.command.toyvillage_server.domain.app.task.presentation.dto.request.TaskRequest;
import com.command.toyvillage_server.domain.app.task.domain.TaskTarget;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.service.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateTaskService {
    private final TaskRepository taskRepository;
    private final TaskTargetService taskTargetService;
    private final FileFacade fileFacade;

    @Transactional
    public void execute(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> TaskNotFoundException.EXCEPTION);

        TaskTarget target = taskTargetService.execute(request.assigneeType(), request.assigneeId());
        List<File> files = request.files() == null ? null : fileFacade.findAllByKeys(request.files());

        task.update(
                request.title(),
                request.content(),
                request.assigneeType(),
                target.employee(),
                target.team(),
                request.finishDate(),
                request.priority(),
                files
        );
    }
}
