package com.command.toyvillage_server.domain.partnership.service;

import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.domain.repository.PartnershipRepository;
import com.command.toyvillage_server.domain.partnership.exception.PartnershipNotFoundException;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnershipQueryService {
    private final PartnershipRepository partnershipRepository;

    @Transactional(readOnly = true)
    public PartnershipResponse execute(Long id) {
        Partnership partnership=  partnershipRepository.findById(id)
                .orElseThrow(() -> PartnershipNotFoundException.EXCEPTION);
                return PartnershipResponse.from(partnership);
    }
}
