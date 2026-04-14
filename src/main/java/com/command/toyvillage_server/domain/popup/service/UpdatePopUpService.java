package com.command.toyvillage_server.domain.popup.service;


import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePopUpService {
    private final PopUpRepository popUpRepository;

    public void execute(PopUpRequest popUpRequest) {

    }
}
