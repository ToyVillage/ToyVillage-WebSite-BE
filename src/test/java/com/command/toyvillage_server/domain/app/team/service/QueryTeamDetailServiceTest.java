package com.command.toyvillage_server.domain.app.team.service;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.exception.TeamNotFoundException;
import com.command.toyvillage_server.domain.app.team.presentation.dto.response.TeamResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryTeamDetailServiceTest {

    @Mock
    private TeamRepository teamRepository;

    private QueryTeamDetailService queryTeamDetailService;

    @BeforeEach
    void setUp() {
        queryTeamDetailService = new QueryTeamDetailService(teamRepository);
    }

    @Test
    void 팀을_상세조회한다() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(Team.create("운영팀")));

        TeamResponse response = queryTeamDetailService.execute(1L);

        assertEquals("운영팀", response.name());
    }

    @Test
    void 존재하지_않는_팀을_조회하면_예외가_발생한다() {
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                TeamNotFoundException.class,
                () -> queryTeamDetailService.execute(1L)
        );
    }
}
