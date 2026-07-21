package com.command.toyvillage_server.domain.app.open_time.presentation.dto.response;

import com.command.toyvillage_server.domain.app.open_time.domain.OpenTime;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record OpenTimeResponse(
        Long id,
        LocalTime startOpenTime,
        LocalTime endOpenTime
) {
    public static OpenTimeResponse from(OpenTime openTime) {
        return OpenTimeResponse.builder()
                .id(openTime.getId())
                .startOpenTime(openTime.getStartOpenTime())
                .endOpenTime(openTime.getEndOpenTime())
                .build();
    }
}
