package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRefreshToken;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRole;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppRefreshTokenRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountNotFoundException;
import com.command.toyvillage_server.global.security.auth.AppAccountDetails;
import com.command.toyvillage_server.global.security.auth.AppAccountDetailsService;
import com.command.toyvillage_server.global.security.exception.ExpiredTokenException;
import com.command.toyvillage_server.global.security.exception.InvalidTokenException;
import com.command.toyvillage_server.global.security.exception.RefreshTokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AppJwtTokenProvider {
    private static final String ISSUER = "toy-village-app";
    private static final String CLAIM_TYPE = "type";
    private static final String CLAIM_ROLE = "role";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    private final AppJwtProperties properties;
    private final AppAccountRepository appAccountRepository;
    private final AppRefreshTokenRepository appRefreshTokenRepository;
    private final AppAccountDetailsService appAccountDetailsService;

    public TokenPair issue(AppAccount account) {
        String username = account.getUsername();
        String role = account.getRole().name();
        String accessToken = createToken(username, role, ACCESS_TYPE, properties.accessExpiration());
        String refreshToken = createToken(username, role, REFRESH_TYPE, properties.refreshExpiration());

        appRefreshTokenRepository.save(
                AppRefreshToken.create(username, refreshToken, properties.refreshExpiration())
        );
        return TokenPair.of(accessToken, refreshToken);
    }

    public TokenPair reissue(String token) {
        Claims claims = parseClaims(token);
        validateBaseClaims(claims, REFRESH_TYPE);

        String username = claims.getSubject();
        AppRefreshToken storedToken = appRefreshTokenRepository.findById(username)
                .filter(saved -> saved.getToken().equals(token))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        AppAccount account = appAccountRepository.findByUsername(username)
                .orElseThrow(() -> AppAccountNotFoundException.EXCEPTION);
        validateRole(claims, account.getRole());

        appRefreshTokenRepository.delete(storedToken);
        return issue(account);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        validateBaseClaims(claims, ACCESS_TYPE);

        UserDetails details = appAccountDetailsService.loadUserByUsername(claims.getSubject());
        if (!(details instanceof AppAccountDetails appAccountDetails)) {
            throw InvalidTokenException.EXCEPTION;
        }
        validateRole(claims, appAccountDetails.appAccount().getRole());

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private String createToken(String subject, String role, String type, long expiration) {
        Date now = new Date();
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(ISSUER)
                .subject(subject)
                .claim(CLAIM_TYPE, type)
                .claim(CLAIM_ROLE, role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(signingKey(), Jwts.SIG.HS512)
                .compact();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private void validateBaseClaims(Claims claims, String expectedType) {
        if (!Objects.equals(ISSUER, claims.getIssuer())
                || !Objects.equals(expectedType, claims.get(CLAIM_TYPE, String.class))) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private void validateRole(Claims claims, AppRole role) {
        if (!Objects.equals(role.name(), claims.get(CLAIM_ROLE, String.class))) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(properties.secretKey().getBytes(StandardCharsets.UTF_8));
    }
}
