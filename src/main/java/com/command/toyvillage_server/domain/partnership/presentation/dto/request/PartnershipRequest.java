package com.command.toyvillage_server.domain.partnership.presentation.dto.request;

import com.command.toyvillage_server.domain.partnership.domain.PartnershipType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PartnershipRequest {
    @NotBlank(message = "제휴 문의 종류는 공백일 수 없습니다.")
    private PartnershipType partnershipType;

    @NotBlank(message = "제휴 제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    private String email;

    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;

    @NotBlank(message = "제휴 내용은 공백일 수 없습니다.")
    private String content;
}
