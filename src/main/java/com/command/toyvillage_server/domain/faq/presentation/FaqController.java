package com.command.toyvillage_server.domain.faq.presentation;

import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqCreateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqUpdateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqDetailResponse;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqListResponse;
import com.command.toyvillage_server.domain.faq.service.*;
import com.command.toyvillage_server.global.dto.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/faq")
@RestController
public class FaqController {
    private final CreateFaqService createFaqService;
    private final QueryFaqDetailService queryFaqDetailService;
    private final QueryFaqListService queryFaqListService;
    private final UpdateFaqService updateFaqService;
    private final DeleteFaqService deleteFaqService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse create(@Valid @RequestBody FaqCreateRequest faqCreateRequest) {
        return createFaqService.execute(faqCreateRequest);
    }

    @GetMapping
    public FaqListResponse getFaqList() {
        return queryFaqListService.execute();
    }

    @GetMapping("/{faq_id}")
    public FaqDetailResponse get(@PathVariable(name = "faq_id") Long faqId) {
        return queryFaqDetailService.execute(faqId);
    }

    @PutMapping("/{faq_id}")
    public MessageResponse update(
        @PathVariable(name = "faq_id") Long faqId,
        @Valid @RequestBody FaqUpdateRequest faqUpdateRequest
    ) {
        return updateFaqService.execute(faqUpdateRequest, faqId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{faq_id}")
    public void delete(@PathVariable(name = "faq_id") Long faqId) {
        deleteFaqService.execute(faqId);
    }
}
