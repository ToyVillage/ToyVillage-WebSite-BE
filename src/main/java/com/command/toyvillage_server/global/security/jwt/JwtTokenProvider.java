package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.common.auth.user.exception.UserNotFoundException;
import com.command.toyvillage_server.domain.common.auth.common.domain.RefreshToken;
import com.command.toyvillage_server.domain.common.auth.admin.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.common.auth.common.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.common.auth.admin.exception.AdminNotFoundException;
import com.command.toyvillage_server.domain.common.auth.common.exception.ExpiredTokenException;
import com.command.toyvillage_server.domain.common.auth.common.exception.InvalidTokenException;
import com.command.toyvillage_server.domain.common.auth.common.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.auth.CustomAppUserDetailsService;
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
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAppUserDetailsService customAppUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String CLAIM_TYPE = "type";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    private static final String CLAIM_ROLE = "role";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)
        );
    }

    public String createAccessToken(String subject, String role) {

        Date now = new Date();

        return Jwts.builder()
                .subject(subject)
                .claim(CLAIM_TYPE, ACCESS_TYPE)
                .claim(CLAIM_ROLE, role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();
    }

    public String createRefreshToken(String subject, String role) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .subject(subject)
                .claim(CLAIM_TYPE, REFRESH_TYPE)
                .claim(CLAIM_ROLE, role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(subject)
                        .token(refreshToken)
                        .timeToLive(jwtProperties.getRefreshExpiration())
                        .build()
        );

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {

        Claims claims = getClaims(token);
        String role = claims.get(CLAIM_ROLE, String.class);

        UserDetails userDetails = ROLE_USER.equals(role)
                ? customAppUserDetailsService.loadUserByUsername(claims.getSubject())
                : customUserDetailsService.loadUserByUsername(claims.getSubject());

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

    public TokenResponse receiveToken(String email) {

        adminRepository.findByEmail(email)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        return TokenResponse.of(
                createAccessToken(email, ROLE_ADMIN),
                createRefreshToken(email, ROLE_ADMIN)
        );
    }

    public TokenResponse receiveUserToken(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TokenResponse.of(
                createAccessToken(email, ROLE_USER),
                createRefreshToken(email, ROLE_USER)
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
