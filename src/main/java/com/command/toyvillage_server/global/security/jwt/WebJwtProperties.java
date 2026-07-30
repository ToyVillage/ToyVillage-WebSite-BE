package com.command.toyvillage_server.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "web.jwt")
public record WebJwtProperties(
        String secretKey,
        long accessExpiration,
        long refreshExpiration
) {
}
