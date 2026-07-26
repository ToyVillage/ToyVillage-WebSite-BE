package com.command.toyvillage_server.domain.app.join_team.presentation;

import com.command.toyvillage_server.domain.app.join_team.service.JoinTeamService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join-team")
@RequiredArgsConstructor
public class JoinTeamController {
    private final JoinTeamService joinTeamService;

    @PutMapping("/{userId}/{teamId}")
    public ResponseEntity<MessageResponse> joinTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        joinTeamService.execute(userId, teamId);

        return ResponseEntity.ok(
                MessageResponse.of("유저가 팀에 배정되었습니다.")
        );
    }
}
