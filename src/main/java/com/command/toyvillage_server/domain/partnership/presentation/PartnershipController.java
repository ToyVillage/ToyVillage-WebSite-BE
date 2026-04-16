package com.command.toyvillage_server.domain.partnership.presentation;

import com.command.toyvillage_server.domain.partnership.presentation.dto.request.PartnershipRequest;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.MessageResponse;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipQueryResponse;
import com.command.toyvillage_server.domain.partnership.presentation.dto.response.PartnershipResponse;
import com.command.toyvillage_server.domain.partnership.service.PartnershipCreateService;
import com.command.toyvillage_server.domain.partnership.service.PartnershipQueryAllService;
import com.command.toyvillage_server.domain.partnership.service.PartnershipQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partnership")
@RequiredArgsConstructor
public class PartnershipController {
    private final PartnershipCreateService partnershipCreateService;
    private final PartnershipQueryAllService partnershipQueryAllService;
    private final PartnershipQueryService partnershipQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid PartnershipRequest partnershipRequest) {
        partnershipCreateService.execute(partnershipRequest);
        return ResponseEntity.ok(
                new MessageResponse("제휴생성이 완료되었습니다.")
        );
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PartnershipQueryResponse> readAll() {
        return partnershipQueryAllService.execute();
    }
    @GetMapping("{/id}")
    @ResponseStatus(HttpStatus.OK)
    public PartnershipResponse readById(@PathVariable Long id) {
        return partnershipQueryService.execute(id);
    }
}
