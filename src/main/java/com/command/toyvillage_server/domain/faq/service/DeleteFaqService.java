package com.command.toyvillage_server.domain.faq.service;

import com.command.toyvillage_server.domain.faq.domain.Faq;
import com.command.toyvillage_server.domain.faq.domain.repository.FaqRepository;
import com.command.toyvillage_server.domain.faq.exception.FaqNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFaqService {
    private final FaqRepository faqRepository;

    @Transactional
    public void execute(Long faqId) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> FaqNotFoundException.EXCEPTION);

        faqRepository.delete(faq);
    }
}
