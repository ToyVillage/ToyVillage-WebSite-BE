package com.command.toyvillage_server.domain.app.join_team.service;

import com.command.toyvillage_server.domain.app.join_team.domain.JoinTeam;
import com.command.toyvillage_server.domain.app.join_team.domain.repository.JoinTeamRepository;
import com.command.toyvillage_server.domain.app.join_team.presentation.dto.request.JoinTeamRequest;
import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.exception.TeamNotFoundException;
import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.common.auth.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinTeamService {
    private final JoinTeamRepository joinTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void execute(JoinTeamRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Team team = teamRepository.findById(request.teamId())
                .orElseThrow(() -> TeamNotFoundException.EXCEPTION);

        JoinTeam joinTeam = joinTeamRepository.findByUser_Id(user.getId())
                .map(savedJoinTeam -> {
                    savedJoinTeam.updateTeam(team);
                    return savedJoinTeam;
                })
                .orElseGet(() -> JoinTeam.create(user, team));

        joinTeamRepository.save(joinTeam);
    }
}
