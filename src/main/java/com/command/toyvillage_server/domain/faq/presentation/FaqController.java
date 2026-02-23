package com.command.toyvillage_server.domain.faq.presentation;

import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqCreateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.request.FaqUpdateRequest;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.FaqResponse;
import com.command.toyvillage_server.domain.faq.service.*;
import com.command.toyvillage_server.domain.faq.presentation.dto.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        createFaqService.execute(faqCreateRequest);
        return MessageResponse.of("자주묻는 질문이 추가되었습니다.");
    }

    @GetMapping
    public List<FaqResponse> getList() {
        return queryFaqListService.execute();
    }

    @GetMapping("/{faq_id}")
    public FaqResponse get(@PathVariable(name = "faq_id") Long faqId) {
        return queryFaqDetailService.execute(faqId);
    }

    @PutMapping("/{faq_id}")
    public MessageResponse update(
        @PathVariable(name = "faq_id") Long faqId,
        @Valid @RequestBody FaqUpdateRequest faqUpdateRequest
    ) {
        updateFaqService.execute(faqUpdateRequest, faqId);
        return MessageResponse.of("자주묻는 질문이 수정되었습니다.");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{faq_id}")
    public void delete(@PathVariable(name = "faq_id") Long faqId) {
        deleteFaqService.execute(faqId);
    }
}
