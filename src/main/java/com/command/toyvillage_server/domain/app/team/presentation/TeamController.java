package com.command.toyvillage_server.domain.app.team.presentation;

import com.command.toyvillage_server.domain.app.team.presentation.dto.request.TeamRequest;
import com.command.toyvillage_server.domain.app.team.presentation.dto.response.TeamResponse;
import com.command.toyvillage_server.domain.app.team.service.QueryTeamDetailService;
import com.command.toyvillage_server.domain.app.team.service.QueryTeamListService;
import com.command.toyvillage_server.domain.app.team.service.TeamCreateService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamCreateService teamCreateService;
    private final QueryTeamListService queryTeamListService;
    private final QueryTeamDetailService queryTeamDetailService;

    @PostMapping
    public ResponseEntity<MessageResponse> createTeam(@RequestBody @Valid TeamRequest request) {
        teamCreateService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("팀이 생성되었습니다."));
    }

    @GetMapping
    public List<TeamResponse> getTeamList() {
        return queryTeamListService.execute();
    }

    @GetMapping("/{teamId}")
    public TeamResponse getTeamDetail(@PathVariable Long teamId) {
        return queryTeamDetailService.execute(teamId);
    }
}
