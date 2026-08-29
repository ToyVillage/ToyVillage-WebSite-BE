package com.command.toyvillage_server.domain.app.task.domain;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.team.domain.Team;

public record TaskTarget(AppAdmin employee, Team team) {
}
