package com.command.toyvillage_server.domain.popup.presentation;

import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import com.command.toyvillage_server.domain.popup.service.CreatePopUpService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/popup")
@RequiredArgsConstructor
public class PopUpController {
    private final CreatePopUpService createPopUpService;

    @PostMapping
    public ResponseEntity<MessageResponse> createPopUp(PopUpRequest popUpRequest) {
        createPopUpService.execute(popUpRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("팝업이 생성되었습니다."));
    }
}
