package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePopUpService {
    private final PopUpRepository popUpRepository;
    private final AwsS3Provider awsS3Provider;

    @Transactional
    public void execute(PopUpRequest popUpRequest) {
        String popupImage = awsS3Provider.upload(popUpRequest.popupImage());

        PopUp popUp = PopUp.builder()
                .popupImage(popupImage)
                .expirationDate(popUpRequest.expirationDate())
                .priority(popUpRequest.priority())
                .build();

        popUpRepository.save(popUp);
    }
}
