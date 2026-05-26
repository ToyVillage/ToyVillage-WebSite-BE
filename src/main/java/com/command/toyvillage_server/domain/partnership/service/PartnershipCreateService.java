package com.command.toyvillage_server.domain.partnership.service;

import com.command.toyvillage_server.domain.file.domain.File;
import com.command.toyvillage_server.domain.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.file.exception.FileNotFoundException;
import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.domain.repository.PartnershipRepository;
import com.command.toyvillage_server.domain.partnership.presentation.dto.request.PartnershipRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnershipCreateService {
    private final PartnershipRepository partnershipRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void execute(PartnershipRequest partnershipRequest) {

        File file = null;
        if (StringUtils.hasText(partnershipRequest.getFileKey())) {
            file = fileRepository.findByFileKey(partnershipRequest.getFileKey())
                    .orElseThrow(() -> FileNotFoundException.EXCEPTION);
        }

        Partnership partnership = Partnership.builder()
                .name(partnershipRequest.getName())
                .title(partnershipRequest.getTitle())
                .content(partnershipRequest.getContent())
                .email(partnershipRequest.getEmail())
                .phoneNumber(partnershipRequest.getPhoneNumber())
                .type(partnershipRequest.getPartnershipType())
                .file(file)
                .build();

        partnershipRepository.save(partnership);
        log.info("제휴 생성  {}", partnership.getId());
    }
}
