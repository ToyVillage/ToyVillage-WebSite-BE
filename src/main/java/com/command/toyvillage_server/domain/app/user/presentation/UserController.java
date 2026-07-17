package com.command.toyvillage_server.domain.app.user.presentation;

import com.command.toyvillage_server.domain.app.user.presentation.dto.response.UserResponse;
import com.command.toyvillage_server.domain.app.user.service.UserQueryListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserQueryListService userQueryListService;

    @GetMapping()
    public List<UserResponse> getUserList(){
        return userQueryListService.execute();
    }
}
