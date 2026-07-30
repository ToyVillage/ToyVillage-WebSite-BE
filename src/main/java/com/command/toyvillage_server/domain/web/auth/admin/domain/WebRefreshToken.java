package com.command.toyvillage_server.domain.web.auth.admin.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash("web_refresh_token")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebRefreshToken {

    @Id
    private String email;

    private String token;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;

    public static WebRefreshToken create(String email, String token, long expiration) {
        return WebRefreshToken.builder()
                .email(email)
                .token(token)
                .expiration(expiration)
                .build();
    }
}
