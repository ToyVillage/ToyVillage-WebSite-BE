package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.Faq;
import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqCreateRequest;
import com.command.toyvillage_server.global.dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateFaqService {
    private final FaqRepository faqRepository;

    @Transactional
    public MessageResponse execute(FaqCreateRequest request) {
        Faq faq = Faq.builder()
            .content(request.getQuestionContent())
            .answer(request.getQuestionAnswer())
            .build();
        faqRepository.save(faq);

        return new MessageResponse("자주묻는 질문이 추가되었습니다.");
    }
}
