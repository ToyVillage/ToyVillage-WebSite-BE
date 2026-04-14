package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePopUpService {
    private final PopUpRepository popUpRepository;

    public void execute(PopUpRequest popUpRequest) {
        PopUp popUp = PopUp.builder()
                .content(popUpRequest.content())
                .contentType(popUpRequest.contentType())
                .expirationDate(popUpRequest.expirationDate())
                .priority(popUpRequest.priority())
                .build();

        popUpRepository.save(popUp);
    }
}
