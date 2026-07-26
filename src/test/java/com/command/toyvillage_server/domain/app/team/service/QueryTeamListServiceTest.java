package com.command.toyvillage_server.domain.app.team.service;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.presentation.dto.response.TeamResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryTeamListServiceTest {

    @Mock
    private TeamRepository teamRepository;

    private QueryTeamListService queryTeamListService;

    @BeforeEach
    void setUp() {
        queryTeamListService = new QueryTeamListService(teamRepository);
    }

    @Test
    void 전체_팀을_조회한다() {
        when(teamRepository.findAllByOrderByIdAsc()).thenReturn(List.of(
                Team.create("운영팀"),
                Team.create("사육팀")
        ));

        List<TeamResponse> response = queryTeamListService.execute();

        assertEquals(2, response.size());
        assertEquals("운영팀", response.get(0).name());
        assertEquals("사육팀", response.get(1).name());
    }
}
