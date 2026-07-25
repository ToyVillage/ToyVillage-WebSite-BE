package com.command.toyvillage_server.domain.app.team.service;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamDeleteService {
    private final TeamRepository teamRepository;

    @Transactional
    public void execute(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> TeamNotFoundException.EXCEPTION);

        teamRepository.delete(team);
    }
}
