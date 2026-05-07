package com.command.toyvillage_server.domain.popup.service;


import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.exception.PopUpNotFoundException;
import com.command.toyvillage_server.domain.popup.presentation.dto.request.PopUpRequest;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePopUpService {
    private final PopUpRepository popUpRepository;
    private final AwsS3Provider awsS3Provider;

    @Transactional
    public void execute(PopUpRequest popUpRequest, Long popUpId) {
        PopUp popUp = popUpRepository.findById(popUpId)
                .orElseThrow(() -> PopUpNotFoundException.EXCEPTION);

        String popupImage = awsS3Provider.upload(popUpRequest.popupImage());

        popUp.update(
                popupImage,
                popUpRequest.expirationDate(),
                popUpRequest.priority()
        );

        popUpRepository.save(popUp);
    }
}
