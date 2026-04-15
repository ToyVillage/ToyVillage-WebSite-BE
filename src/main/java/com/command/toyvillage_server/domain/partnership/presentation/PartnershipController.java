package com.command.toyvillage_server.domain.partnership.presentation;

import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.presentation.dto.request.PartnershipRequest;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.MessageResponse;
import com.command.toyvillage_server.domain.partnership.service.PartnershipCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partnership")
@RequiredArgsConstructor
public class PartnershipController {
    private final PartnershipCreateService partnershipCreateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> execute(@RequestBody @Valid PartnershipRequest partnershipRequest) {
        partnershipCreateService.execute(partnershipRequest);
        return ResponseEntity.ok(
                new MessageResponse("제휴생성이 완료되었습니다.")
        );
    }
}
