package com.command.toyvillage_server.domain.partnership.presentation.dto.response;

import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.domain.PartnershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PartnershipResponse {
    private Long id;
    private String title;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String content;
    private PartnershipType type;

    public static PartnershipResponse from(Partnership partnership) {
        return new PartnershipResponse(
                partnership.getId(),
                partnership.getTitle(),
                partnership.getEmail(),
                partnership.getPhoneNumber(),
                partnership.getCreatedDate(),
                partnership.getContent(),
                partnership.getType()
        );
    }


}
