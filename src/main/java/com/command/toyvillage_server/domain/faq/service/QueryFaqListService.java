package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqDetailResponse;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryFaqListService {
    private final FaqRepository faqRepository;

    @Transactional(readOnly = true)
    public FaqListResponse execute() {
        List<FaqDetailResponse> faqDetailResponseList = faqRepository.findAll().stream()
            .map(faq -> FaqDetailResponse.builder()
                .questionId(faq.getId())
                .questionContent(faq.getContent())
                .questionAnswer(faq.getAnswer())
                .build())
            .toList();

        return FaqListResponse.builder()
            .faq(faqDetailResponseList)
            .build();
    }
}
