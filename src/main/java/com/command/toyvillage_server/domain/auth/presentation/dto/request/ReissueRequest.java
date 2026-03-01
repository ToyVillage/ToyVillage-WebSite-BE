package com.command.toyvillage_server.domain.auth.presentation.dto.request;

public record ReissueRequest(
        String username
) {
    public static ReissueRequest of(String username) {
        return new ReissueRequest(username);
    }
}
