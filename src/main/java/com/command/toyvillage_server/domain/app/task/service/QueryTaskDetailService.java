package com.command.toyvillage_server.domain.app.task.service;

import com.command.toyvillage_server.domain.app.auth.admin.facade.UserFacade;
import com.command.toyvillage_server.domain.app.join_team.domain.repository.JoinTeamRepository;
import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.task.exception.TaskNotFoundException;
import com.command.toyvillage_server.domain.app.task.presentation.dto.response.TaskDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryTaskDetailService {
    private final TaskRepository taskRepository;
    private final JoinTeamRepository joinTeamRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public TaskDetailResponse execute(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> TaskNotFoundException.EXCEPTION);

        if (!userFacade.isCurrentUserAppAdmin() && !isAssignee(task, userFacade.getCurrentUserId())) {
            throw TaskNotFoundException.EXCEPTION;
        }

        return TaskDetailResponse.from(task);
    }

    private boolean isAssignee(Task task, Long appAdminId) {
        if (task.getAssigneeType() == TaskAssigneeType.ALL) {
            return true;
        }

        if (task.getAssignee() != null && task.getAssignee().getId().equals(appAdminId)) {
            return true;
        }

        return task.getAssigneeTeam() != null
                && joinTeamRepository.existsByAppAdmin_IdAndTeam_Id(appAdminId, task.getAssigneeTeam().getId());
    }
}
