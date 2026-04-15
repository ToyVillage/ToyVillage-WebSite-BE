package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.presentation.dto.response.PopUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryListPopUpService {
    private final PopUpRepository popUpRepository;

    @Transactional(readOnly = true)
    public List<PopUpResponse> execute(){
        List<PopUp> popUps = popUpRepository.findAll();

        return popUps
                .stream()
                .map(PopUpResponse::from)
                .toList();
    }
}
