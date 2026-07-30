package com.command.toyvillage_server.domain.app.join_team.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountNotFoundException;
import com.command.toyvillage_server.domain.app.join_team.domain.JoinTeam;
import com.command.toyvillage_server.domain.app.join_team.domain.repository.JoinTeamRepository;
import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.team.domain.repository.TeamRepository;
import com.command.toyvillage_server.domain.app.team.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinTeamService {
    private final JoinTeamRepository joinTeamRepository;
    private final AppAccountRepository appAccountRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void execute(Long appAccountId, Long teamId) {
        AppAccount appAccount = appAccountRepository.findById(appAccountId)
                .orElseThrow(() -> AppAccountNotFoundException.EXCEPTION);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> TeamNotFoundException.EXCEPTION);

        JoinTeam joinTeam = joinTeamRepository.findByAppAccount_Id(appAccount.getId())
                .map(savedJoinTeam -> {
                    savedJoinTeam.updateTeam(team);
                    return savedJoinTeam;
                })
                .orElseGet(() -> JoinTeam.create(appAccount, team));

        joinTeamRepository.save(joinTeam);
    }
}
