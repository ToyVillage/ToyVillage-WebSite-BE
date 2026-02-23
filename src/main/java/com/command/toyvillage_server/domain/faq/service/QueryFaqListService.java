package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryFaqListService {
    private final FaqRepository faqRepository;

    @Transactional(readOnly = true)
    public List<FaqResponse> execute() {
        return faqRepository.findAll().stream()
            .map(FaqResponse::from)
            .toList();
    }
}
