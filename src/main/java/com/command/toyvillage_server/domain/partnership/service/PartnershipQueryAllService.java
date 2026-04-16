package com.command.toyvillage_server.domain.partnership.service;

import com.command.toyvillage_server.domain.partnership.domain.repository.PartnershipRepository;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipQueryResponse;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnershipQueryAllService {
    private final PartnershipRepository partnershipRepository;

    @Transactional(readOnly = true)
    public List<PartnershipQueryResponse> execute() {
        return partnershipRepository.findAll()
                .stream()
                .map(PartnershipQueryResponse::from)
                .collect(Collectors.toList());
    }
}
