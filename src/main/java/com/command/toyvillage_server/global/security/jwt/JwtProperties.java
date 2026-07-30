package com.command.toyvillage_server.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String header;
    private final String prefix;
    private final String webSecretKey;
    private final String appSecretKey;
    private final Long webAccessExpiration;
    private final Long webRefreshExpiration;
    private final Long appAccessExpiration;
    private final Long appRefreshExpiration;

    public JwtProperties(
            String header,
            String prefix,
            String webSecretKey,
            String appSecretKey,
            Long webAccessExpiration,
            Long webRefreshExpiration,
            Long appAccessExpiration,
            Long appRefreshExpiration
    ) {
        this.header = header;
        this.prefix = prefix;
        this.webSecretKey = encodeSecretKey(webSecretKey);
        this.appSecretKey = encodeSecretKey(appSecretKey);
        this.webAccessExpiration = webAccessExpiration;
        this.webRefreshExpiration = webRefreshExpiration;
        this.appAccessExpiration = appAccessExpiration;
        this.appRefreshExpiration = appRefreshExpiration;
    }

    private String encodeSecretKey(String secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
