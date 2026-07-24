package com.command.toyvillage_server.domain.app.team.presentation;

import com.command.toyvillage_server.domain.app.team.presentation.dto.request.TeamRequest;
import com.command.toyvillage_server.domain.app.team.service.TeamCreateService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamCreateService teamCreateService;

    @PostMapping
    public ResponseEntity<MessageResponse> createTeam(@RequestBody @Valid TeamRequest request) {
        teamCreateService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("팀이 생성되었습니다."));
    }
}
