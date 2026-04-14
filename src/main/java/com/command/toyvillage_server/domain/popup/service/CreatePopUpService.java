package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePopUpService {
    private final PopUpRepository popUpRepository;

    public void execute(){

    }
}
