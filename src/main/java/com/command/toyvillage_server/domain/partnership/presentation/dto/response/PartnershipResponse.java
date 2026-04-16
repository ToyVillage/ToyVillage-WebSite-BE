package com.command.toyvillage_server.domain.partnership.presentation.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PartnershipResponse {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
