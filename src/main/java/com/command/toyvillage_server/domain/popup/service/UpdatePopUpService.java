package com.command.toyvillage_server.domain.popup.service;


import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.exception.PopUpNotFoundException;
import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePopUpService {
    private final PopUpRepository popUpRepository;

    @Transactional
    public void execute(PopUpRequest popUpRequest, Long popUpId) {
        PopUp popUp = popUpRepository.findById(popUpId)
                .orElseThrow(() -> PopUpNotFoundException.EXCEPTION);

        popUp.update(
                popUpRequest.content(),
                popUpRequest.contentType(),
                popUpRequest.expirationDate(),
                popUpRequest.priority()
        );

        popUpRepository.save(popUp);
    }
}
