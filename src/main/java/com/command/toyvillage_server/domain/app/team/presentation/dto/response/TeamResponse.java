package com.command.toyvillage_server.domain.app.team.presentation.dto.response;

import com.command.toyvillage_server.domain.app.team.domain.Team;

public record TeamResponse(
        Long id,
        String name
) {
    public static TeamResponse from(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getName()
        );
    }
}
