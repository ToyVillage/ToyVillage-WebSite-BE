package com.command.toyvillage_server.domain.app.team.service;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.presentation.dto.request.TeamRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeamCreateServiceTest {

    @Mock
    private TeamRepository teamRepository;

    private TeamCreateService teamCreateService;

    @BeforeEach
    void setUp() {
        teamCreateService = new TeamCreateService(teamRepository);
    }

    @Test
    void 팀을_생성한다() {
        TeamRequest request = new TeamRequest("운영팀");

        teamCreateService.execute(request);

        ArgumentCaptor<Team> teamCaptor = ArgumentCaptor.forClass(Team.class);
        verify(teamRepository).save(teamCaptor.capture());
        assertEquals("운영팀", teamCaptor.getValue().getName());
    }
}
