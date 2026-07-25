package com.command.toyvillage_server.domain.app.join_team.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record JoinTeamRequest(
        @NotNull(message = "유저를 선택해주세요.")
        Long userId,

        @NotNull(message = "팀을 선택해주세요.")
        Long teamId
) {
}
