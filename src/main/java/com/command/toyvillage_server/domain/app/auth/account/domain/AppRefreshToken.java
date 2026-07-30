package com.command.toyvillage_server.domain.app.auth.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@Builder
@RedisHash("app_refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppRefreshToken {

    @Id
    private String username;

    private String token;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;

    public static AppRefreshToken create(String username, String token, long expiration) {
        return AppRefreshToken.builder()
                .username(username)
                .token(token)
                .expiration(expiration)
                .build();
    }
}
