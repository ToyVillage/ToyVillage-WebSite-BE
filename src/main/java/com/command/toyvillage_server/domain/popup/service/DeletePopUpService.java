package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.exception.PopUpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePopUpService {
    private final PopUpRepository popUpRepository;

    public void execute(Long popUpId){
        PopUp popUp = popUpRepository.findById(popUpId)
                .orElseThrow(() -> PopUpNotFoundException.EXCEPTION);

        popUpRepository.delete(popUp);
    }
}
