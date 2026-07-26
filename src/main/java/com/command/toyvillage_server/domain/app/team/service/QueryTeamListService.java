package com.command.toyvillage_server.domain.app.team.service;

import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.presentation.dto.response.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryTeamListService {
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public List<TeamResponse> execute() {
        return teamRepository.findAllByOrderByIdAsc()
                .stream()
                .map(TeamResponse::from)
                .toList();
    }
}
