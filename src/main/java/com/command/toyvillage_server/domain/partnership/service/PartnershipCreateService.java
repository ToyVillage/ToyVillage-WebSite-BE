package com.command.toyvillage_server.domain.partnership.service;

import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.domain.repository.PartnershipRepository;
import com.command.toyvillage_server.domain.partnership.presentation.dto.request.PartnershipRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnershipCreateService {
        private final PartnershipRepository partnershipRepository;

    @Transactional
    public void execute(PartnershipRequest partnershipRequest) {
        Partnership partnership = Partnership.builder()
                .name(partnershipRequest.getName())
                .title(partnershipRequest.getTitle())
                .content(partnershipRequest.getContent())
                .email(partnershipRequest.getEmail())
                .phoneNumber(partnershipRequest.getPhoneNumber())
                .type(partnershipRequest.getPartnershipType())
                .build();
        partnershipRepository.save( partnership);
        log.info("제휴 생성  {}", partnership.getId());
    }
}
