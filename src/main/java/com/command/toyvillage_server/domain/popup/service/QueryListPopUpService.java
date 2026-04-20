package com.command.toyvillage_server.domain.popup.service;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.repository.PopUpRepository;
import com.command.toyvillage_server.domain.popup.presentation.dto.response.PopUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryListPopUpService {
    private final PopUpRepository popUpRepository;

    @Transactional(readOnly = true)
    public Page<PopUpResponse> execute(Pageable p) {
        Pageable pageable = PageRequest.of(
                p.getPageNumber(),
                p.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );
        return popUpRepository.findAllBy(pageable)
                .map(PopUpResponse::from);
    }
}
