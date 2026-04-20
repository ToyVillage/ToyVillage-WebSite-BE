package com.command.toyvillage_server.domain.partnership.service;

import com.command.toyvillage_server.domain.partnership.domain.repository.PartnershipRepository;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipQueryAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnershipQueryAllService {
    private final PartnershipRepository partnershipRepository;

    @Transactional(readOnly = true)
    public Page<PartnershipQueryAllResponse> execute(Pageable pageable) {
        return partnershipRepository.findAll(pageable)
                .map(PartnershipQueryAllResponse::from);
    }
}
