package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.Faq;
import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqCreateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateFaqService {
    private final FaqRepository faqRepository;

    @Transactional
    public void execute(FaqCreateRequest request) {
        Faq faq = Faq.builder()
            .content(request.getQuestionContent())
            .answer(request.getQuestionAnswer())
            .build();
        faqRepository.save(faq);
    }
}
