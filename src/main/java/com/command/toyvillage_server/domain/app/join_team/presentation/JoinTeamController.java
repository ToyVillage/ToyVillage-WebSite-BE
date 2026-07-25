package com.command.toyvillage_server.domain.app.join_team.presentation;

import com.command.toyvillage_server.domain.app.join_team.presentation.dto.request.JoinTeamRequest;
import com.command.toyvillage_server.domain.app.join_team.service.JoinTeamService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join-team")
@RequiredArgsConstructor
public class JoinTeamController {
    private final JoinTeamService joinTeamService;

    @PostMapping
    public ResponseEntity<MessageResponse> joinTeam(@RequestBody @Valid JoinTeamRequest request) {
        joinTeamService.execute(request);

        return ResponseEntity.ok(
                MessageResponse.of("유저가 팀에 배정되었습니다.")
        );
    }
}
