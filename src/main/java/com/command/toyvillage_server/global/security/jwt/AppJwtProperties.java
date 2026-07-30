package com.command.toyvillage_server.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record AppJwtProperties(
        String secretKey,
        long accessExpiration,
        long refreshExpiration
) {
}
