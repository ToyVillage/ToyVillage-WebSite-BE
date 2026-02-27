package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.auth.domain.RefreshToken;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
import com.command.toyvillage_server.domain.auth.exception.ExpiredTokenException;
import com.command.toyvillage_server.domain.auth.exception.InvalidTokenException;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AdminRepository adminRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String CLAIM_TYPE = "type";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)
        );
    }

    public String createAccessToken(String adminName) {

        Date now = new Date();

        return Jwts.builder()
                .subject(adminName)
                .claim(CLAIM_TYPE, ACCESS_TYPE)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();
    }

    public String createRefreshToken(String adminName) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .subject(adminName)
                .claim(CLAIM_TYPE, REFRESH_TYPE)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(adminName)
                        .token(refreshToken)
                        .timeToLive(jwtProperties.getRefreshExpiration())
                        .build()
        );

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {

        Claims claims = getClaims(token);

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    public Claims getClaims(String token) {

        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String adminName) {

        adminRepository.findByUsername(adminName)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        return TokenResponse.of(
                createAccessToken(adminName),
                createRefreshToken(adminName)
        );
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {

            return bearerToken.substring(jwtProperties.getPrefix().length() + 1);
        }

        return null;
    }
}
