package com.command.toyvillage_server.domain.partnership.presentation.dto.response;

import com.command.toyvillage_server.domain.partnership.domain.Partnership;
import com.command.toyvillage_server.domain.partnership.domain.PartnershipType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PartnershipResponse {
    private Long id;

    @JsonProperty("partnership_title")
    private String title;

    @JsonProperty("partnership_email")
    private String email;

    @JsonProperty("partnership_phone_number")
    private String phoneNumber;

    @JsonProperty("partnership_created_at")
    private LocalDateTime createdAt;

    @JsonProperty("partnership_content")
    private String content;

    @JsonProperty("partnership_type")
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
