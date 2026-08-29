package com.command.toyvillage_server.domain.app.task.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.auth.admin.domain.repository.AppAdminRepository;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.TaskTarget;
import com.command.toyvillage_server.domain.app.task.exception.TaskTargetInvalidException;
import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTargetService {
    private final AppAdminRepository appAdminRepository;
    private final TeamRepository teamRepository;

    public TaskTarget execute(TaskAssigneeType assigneeType, Long assigneeId) {
        return switch (assigneeType) {
            case ALL -> {
                if (assigneeId != null) {
                    throw TaskTargetInvalidException.EXCEPTION;
                }
                yield new TaskTarget(null, null);
            }
            case EMPLOYEE -> new TaskTarget(findEmployee(assigneeId), null);
            case TEAM -> new TaskTarget(null, findTeam(assigneeId));
        };
    }

    private AppAdmin findEmployee(Long assigneeId) {
        if (assigneeId == null) {
            throw TaskTargetInvalidException.EXCEPTION;
        }
        return appAdminRepository.findById(assigneeId)
                .orElseThrow(() -> AppAdminNotFoundException.EXCEPTION);
    }

    private Team findTeam(Long assigneeId) {
        if (assigneeId == null) {
            throw TaskTargetInvalidException.EXCEPTION;
        }
        return teamRepository.findById(assigneeId)
                .orElseThrow(() -> TeamNotFoundException.EXCEPTION);
    }
}
