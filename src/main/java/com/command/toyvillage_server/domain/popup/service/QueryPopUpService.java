package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.exception.PopUpNotFoundException;
import com.command.toyvillage_server.domain.popup.presentation.dto.response.PopUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryPopUpService {
    private final PopUpRepository popUpRepository;

    public PopUpResponse execute(Long id) {
        PopUp popUp = popUpRepository.findById(id)
                .orElseThrow(() -> PopUpNotFoundException.EXCEPTION);

        return PopUpResponse.from(popUp);
    }
}
