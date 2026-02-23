package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.Faq;
import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.exception.FaqNotFoundException;
import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqUpdateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateFaqService {
    private final FaqRepository faqRepository;

    @Transactional
    public void execute(FaqUpdateRequest request, Long faqId) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> FaqNotFoundException.EXCEPTION);
        faq.update(request.getQuestionContent(), request.getQuestionAnswer());
    }
}