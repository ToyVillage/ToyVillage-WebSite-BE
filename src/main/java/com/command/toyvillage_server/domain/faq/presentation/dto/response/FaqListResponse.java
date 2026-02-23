package com.command.toyvillage_server.domain.faq.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FaqListResponse {
    private List<FaqDetailResponse> faq;
}
